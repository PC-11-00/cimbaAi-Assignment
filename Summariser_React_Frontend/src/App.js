// App.js
import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import FormPage from './components/FormPage';
import HistoryPage from './components/HistoryPage';
import './styles.css'; // Import the CSS file

const App = () => {
  const [history, setHistory] = useState([]);
  const [currentSummary, setCurrentSummary] = useState('');

  const handleSummarize = async (url) => {
    try {
      const response = await fetch('http://localhost:8080/api/summarize', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ url }),
      });
  
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
  
      const data = await response.text(); // Get the response as text
      setCurrentSummary(data); // Set the current summary
      setHistory([...history, { url, summary: data }]); // Add the received data to history
    } catch (error) {
      console.error('Error summarizing the website:', error);
    }
  };
  

  return (
    <Router>
      <div>
        <nav>
          <ul>
            <li>
              <Link to="/">Home</Link>
            </li>
            <li>
              <Link to="/history">History</Link>
            </li>
          </ul>
        </nav>
        <Routes>
          <Route path="/" element={<FormPage onSubmit={handleSummarize} summary={currentSummary} />} />
          <Route path="/history" element={<HistoryPage history={history} />} />
        </Routes>
      </div>
    </Router>
  );
};

export default App;
