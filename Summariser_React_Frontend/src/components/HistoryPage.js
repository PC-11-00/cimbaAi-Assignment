import React, { useEffect, useState } from 'react';

const HistoryPage = () => {
  const [history, setHistory] = useState([]);

  useEffect(() => {
    const fetchHistory = async () => {
      try {
        const response = await fetch('http://localhost:8080/history');
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        const data = await response.text(); // Get response as text
        const parsedData = parseHistoryData(data); // Parse the response text
        setHistory(parsedData); // Set the history state
        console.log('History:', parsedData);
      } catch (error) {
        console.error('Error fetching history:', error);
      }
    };

    fetchHistory();
  }, []);

  // Function to parse the response data into an array of objects
  const parseHistoryData = (data) => {
    const entries = data.split('\n'); // Split the text by new lines
    return entries.map(entry => {
      const [url, summary] = entry.split(', Summary: '); // Split each entry by ', Summary: '
      return { url: url.substring(5), summary: summary.trim() }; // Trim the URL and summary and create an object
    });
  };

  return (
    <div className="container">
      <h1>History of Requests</h1>
      <ul>
        {history.map((entry, index) => (
          <li key={index}>
            <strong>URL:</strong> {entry.url}<br />
            <strong>Summary:</strong> {entry.summary}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default HistoryPage;
