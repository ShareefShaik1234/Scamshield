import { useState } from "react";
import api from "../services/api";
import "../styles/JobScanner.css";

function JobScanner() {

  const [companyName, setCompanyName] = useState("");
  const [email, setEmail] = useState("");
  const [salary, setSalary] = useState("");
  const [jobDescription, setJobDescription] = useState("");
  const [result, setResult] = useState(null);

  const scanJob = async () => {

    if (!companyName || !email || !salary || !jobDescription) {
      alert("Please fill all fields.");
      return;
    }

    try {

      const response = await api.post("/job/scan", {
        companyName,
        email,
        salary: Number(salary),
        jobDescription
      });

      setResult(response.data);

    } catch (error) {

      console.log(error);
      alert("Unable to scan job.");

    }

  };

  return (

    <div className="job-container">

      <div className="job-box">

        <h1>💼 AI Job Scam Detector</h1>

        <p className="subtitle">
          Analyze job offers and detect fake recruitment scams using AI.
        </p>

        <input
          type="text"
          placeholder="Company Name"
          value={companyName}
          onChange={(e) => setCompanyName(e.target.value)}
        />

        <input
          type="email"
          placeholder="Recruiter Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />

        <input
          type="number"
          placeholder="Expected Salary"
          value={salary}
          onChange={(e) => setSalary(e.target.value)}
        />

        <textarea
          rows="6"
          placeholder="Paste complete job description..."
          value={jobDescription}
          onChange={(e) => setJobDescription(e.target.value)}
        />

        <button onClick={scanJob}>
          🔍 Analyze Job
        </button>

        {result && (

          <div className={`result ${result.status.toLowerCase()}`}>

            <h2>Job Scan Report</h2>

            <div className="result-item">
              <span>Status</span>
              <strong>
                {result.status === "SAFE"
                  ? "🟢 Legitimate"
                  : result.status === "SUSPICIOUS"
                  ? "🟡 Suspicious"
                  : "🔴 Scam Detected"}
              </strong>
            </div>

            <div className="result-item">
              <span>Risk Score</span>
              <strong>{result.riskScore}</strong>
            </div>

            <div className="result-item">
              <span>Reason</span>
              <strong>{result.reason}</strong>
            </div>

          </div>

        )}

      </div>

    </div>

  );

}

export default JobScanner;