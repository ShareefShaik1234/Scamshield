import { useState } from "react";
import api from "../services/api";
import "../styles/UrlScanner.css";

function UrlScanner() {

  const [url, setUrl] = useState("");
  const [result, setResult] = useState(null);
  const [loading, setLoading] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");
const scanUrl = async () => {

    if (!url) {
        alert("Please enter a URL");
        return;
    }
setErrorMessage("");
setResult(null);
    try {

        setLoading(true);

        const response = await api.post("/scan/url", {
            url: url
        });

        setResult(response.data);

    } catch (error) {

    console.log(error);

    if (error.response) {

        setErrorMessage(
            typeof error.response.data === "string"
                ? error.response.data
                : error.response.data.message
        );

    } else {

        setErrorMessage("Unable to connect to the server.");

    }

} finally {

        setLoading(false);

    }

};

  return (
    <div className="url-container">

      <div className="url-box">

        <h1>🌐 URL Scanner</h1>

        <p>
          Scan any website to detect phishing or malicious links.
        </p>

        <input
          type="text"
          placeholder="Enter URL..."
          value={url}
          onChange={(e) => setUrl(e.target.value)}
        />

        <button onClick={scanUrl} disabled={loading}>
    {loading ? "🔄 Scanning..." : "🔍 Scan URL"}
</button>

{loading && (

    <div className="loading-text">

        🛡 AI is analyzing the website...

    </div>

)}

{errorMessage && (

    <div className="error-box">

        ❌ {errorMessage}

    </div>

)}
        {result && (

  <div className={`result ${result.status.toLowerCase()}`}>

    <h2>🔍 Scan Report</h2>

    <div className="result-item">
      <span>Status</span>
      <strong>
  {result.status === "SAFE"
    ? "🟢 Safe"
    : result.status === "SUSPICIOUS"
    ? "🟡 Suspicious"
    : "🔴 Malicious"}
</strong>
    </div>

    <div className="result-item">
      <span>Risk Score</span>
      <strong>{result.riskScore}</strong>
    </div>

    <div className="result-item">
      <span>Analysis</span>
      <strong>
  {result.reason}
  {result.status === "SAFE" &&
    " - This website appears to be legitimate."}
  {result.status === "SUSPICIOUS" &&
    " - Browse with caution."}
  {result.status === "MALICIOUS" &&
    " - Avoid visiting this website."}
</strong>
    </div>

  </div>

)}

      </div>

    </div>
  );
}

export default UrlScanner;