import { useEffect, useState } from "react";
import api from "../services/api";
import "../styles/Analytics.css";

function Analytics() {

    const [analytics, setAnalytics] = useState(null);

    useEffect(() => {
        loadAnalytics();
    }, []);

    const loadAnalytics = async () => {

        try {

            const response = await api.get("/analytics");
            setAnalytics(response.data);

        } catch (error) {

            console.error(error);
            alert("Failed to load analytics.");

        }

    };

    if (!analytics) {

        return (
            <div className="loading-container">
                <h2 className="loading">Loading Analytics...</h2>
            </div>
        );

    }

    return (

        <div className="analytics-container">

            <div className="analytics-header">

                <h1>📊 AI Security Analytics</h1>

                <p>
                    Monitor ScamShield activity and track real-time scam detection statistics.
                </p>

            </div>

            <div className="analytics-grid">

                <div className="analytics-card">
                    <h3>👥 Total Users</h3>
                    <h2>{analytics.totalUsers}</h2>
                </div>

                <div className="analytics-card">
                    <h3>🌐 URL Scans</h3>
                    <h2>{analytics.totalUrlScans}</h2>
                </div>

                <div className="analytics-card">
                    <h3>💼 Job Scans</h3>
                    <h2>{analytics.totalJobScans}</h2>
                </div>

                <div className="analytics-card">
                    <h3>📧 Email Scans</h3>
                    <h2>{analytics.totalEmailScans}</h2>
                </div>

                <div className="analytics-card">
                    <h3>📱 SMS Scans</h3>
                    <h2>{analytics.totalSmsScans}</h2>
                </div>

                <div className="analytics-card">
                    <h3>📋 Community Reports</h3>
                    <h2>{analytics.totalReports}</h2>
                </div>

                <div className="analytics-card safe">
                    <h3>🟢 Safe Scans</h3>
                    <h2>{analytics.safeScans}</h2>
                </div>

                <div className="analytics-card scam">
                    <h3>🔴 Scam Scans</h3>
                    <h2>{analytics.scamScans}</h2>
                </div>

            </div>

        </div>

    );

}

export default Analytics;