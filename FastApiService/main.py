from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
import logging
import openai
from fastapi.middleware.cors import CORSMiddleware
import os
from dotenv import load_dotenv
import requests
from bs4 import BeautifulSoup

# Load environment variables
load_dotenv()
openai.api_key = ''

# Initialize FastAPI app
app = FastAPI()

# CORS configuration
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# Enhanced logging configuration
logging.basicConfig(
    level=logging.DEBUG,
    format='%(asctime)s - %(name)s - %(levelname)s - %(message)s',
    handlers=[logging.StreamHandler()]
)

logger = logging.getLogger(__name__)

class URLInput(BaseModel):
    url: str

@app.post("/api/summarize")
async def summarize(input: URLInput):
    try:
        logger.info(f"Received URL for summarization: {input.url}")

        # Fetch the text content of the URL
        response = requests.get(input.url)
        if response.status_code != 200:
            raise HTTPException(status_code=response.status_code, detail="Failed to fetch URL content")

        soup = BeautifulSoup(response.content, 'html.parser')
        text = soup.get_text()

        # Pass the text to OpenAI API for summarization using the chat completions endpoint
        openai_response = openai.ChatCompletion.create(
            model="gpt-3.5-turbo",
            messages=[{"role": "user", "content": f"Summarize the following text: {text}"}],
            max_tokens=150
        )

        summary = openai_response.choices[0].message["content"].strip()

        logger.info(f"Generated summary: {summary}")

        return {"summary": summary}
    except Exception as e:
        logger.error(f"An unexpected error occurred: {str(e)}")
        raise HTTPException(status_code=500, detail=f"An unexpected error occurred: {str(e)}")

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="127.0.0.1", port=8000)
