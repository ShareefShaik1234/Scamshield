import { useState } from "react";
import api from "../services/api";
import "../styles/MessageScanner.css";

function MessageScanner() {

    const [type, setType] = useState("EMAIL");
    const [content, setContent] = useState("");
    const [result, setResult] = useState(null);
    const [loading, setLoading] = useState(false);

    const analyze = async () => {

        if (content.trim() === "") {
            alert("Please enter a message.");
            return;
        }

        try {

            setLoading(true);

            const endpoint =
                type === "EMAIL"
                    ? "/scan/email"
                    : "/scan/sms";

            const response = await api.post(endpoint, {
                content
            });

            setResult(response.data);

        } catch (err) {

            console.error(err);

            if (err.response) {
                alert("Server Error : " + err.response.status);
            } else {
                alert("Unable to connect to backend.");
            }

        } finally {
            setLoading(false);
        }
    };

    return (

        <div className="message-container">

            <div className="message-box">

                <h1>📧 AI Message Scam Detector</h1>

                <p className="subtitle">
                    Analyze suspicious Emails and SMS messages for phishing,
                    fraud, and scam attempts.
                </p>

                <select
                    value={type}
                    onChange={(e) => setType(e.target.value)}
                >
                    <option value="EMAIL">📧 Email</option>
                    <option value="SMS">📱 SMS</option>
                </select>

                <textarea
                    rows="8"
                    placeholder="Paste Email or SMS content here..."
                    value={content}
                    onChange={(e) => setContent(e.target.value)}
                />

                <button onClick={analyze}>
                    {loading ? "Analyzing..." : "🔍 Analyze Message"}
                </button>

                {result && (

                    <div className={`result ${result.status.toLowerCase()}`}>

                        <h2>📋 Scan Report</h2>

                        <div className="result-item">
                            <span>Message Type</span>
                            <strong>{result.messageType}</strong>
                        </div>

                        <div className="result-item">
                            <span>Status</span>

                            <strong>

                                {result.status === "SAFE"
                                    ? "🟢 Safe"
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

                        <div className="result-item">
                            <span>Scan Date</span>
                            <strong>{result.scanDate}</strong>
                        </div>

                    </div>

                )}

            </div>

        </div>

    );

}

export default MessageScanner;