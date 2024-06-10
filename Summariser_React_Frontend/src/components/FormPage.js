// FormPage.js
import React, { useState } from 'react';

const FormPage = ({ onSubmit, summary }) => {
  const [url, setUrl] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit(url);
  };

  return (
    <div>
      <h1>Summarize a Website</h1>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          value={url}
          onChange={(e) => setUrl(e.target.value)}
          placeholder="Enter website URL"
        />
        <button type="submit">Summarize</button>
      </form>
      {summary && (
        <div>
          <h2>Summary</h2>
          <p>{summary}</p>
        </div>
      )}
    </div>
  );
};

export default FormPage;
