import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/Dashboard.css";

function Dashboard() {
  const navigate = useNavigate();
  const [user, setUser] = useState(null);

  useEffect(() => {
    const loggedUser = JSON.parse(localStorage.getItem("user"));

    if (!loggedUser) {
      navigate("/");
      return;
    }

    setUser(loggedUser);
  }, [navigate]);

  const logout = () => {
    localStorage.removeItem("user");
    navigate("/");
  };

  return (
    <div className="dashboard">

      {/* Sidebar */}
      <div className="sidebar">

        <div className="logo">
          <h2>🛡 ScamShield</h2>
          <p>Cyber Security Platform</p>
        </div>

        <ul>

          <li onClick={() => navigate("/dashboard")}>
            🏠 Dashboard
          </li>

          <li onClick={() => navigate("/url-scanner")}>
            🌐 URL Scanner
          </li>

          <li onClick={() => navigate("/job-scanner")}>
            💼 Job Scanner
          </li>

          <li onClick={() => navigate("/message-scanner")}>
            📧 Message Scanner
          </li>

          <li onClick={() => navigate("/reports")}>
            📋 Reports
          </li>

          <li onClick={() => navigate("/analytics")}>
            📊 Analytics
          </li>

          <li onClick={() => navigate("/admin")}>
            👨‍💼 Admin Panel
          </li>

          <li onClick={logout}>
            🚪 Logout
          </li>

        </ul>

      </div>

      {/* Main Content */}
      <div className="main-content">

        {/* Hero Section */}
        <div className="hero">

          <h4>🛡 Cybersecurity Dashboard</h4>

          <h1>
            Welcome Back,
            <span> {user?.name}</span>
          </h1>

          <p>
            Stay protected from phishing websites, fake job postings,
            scam emails, SMS fraud, and online threats using ScamShield.
          </p>

        </div>

{/* Statistics */}

<div className="stats">

  <div className="stat-card">

    <div className="stat-icon">🛡</div>

    <div>
      <h3>Enabled</h3>
      <p>AI Protection</p>
    </div>

  </div>

  <div className="stat-card">

    <div className="stat-icon">🌐</div>

    <div>
      <h3>Active</h3>
      <p>URL Detection</p>
    </div>

  </div>

  <div className="stat-card">

    <div className="stat-icon">📧</div>

    <div>
      <h3>Ready</h3>
      <p>Message Scanner</p>
    </div>

  </div>

  <div className="stat-card">

    <div className="stat-icon">⚡</div>

    <div>
      <h3>Online</h3>
      <p>System Status</p>
    </div>

  </div>

</div>

        {/* Modules */}
        <div className="cards">

          <div
            className="card"
            onClick={() => navigate("/url-scanner")}
          >

            <div className="icon">🌐</div>

            <h3>URL Scanner</h3>

            <p>
              Detect malicious websites and phishing URLs instantly.
            </p>

            <span>Open →</span>

          </div>

          <div
            className="card"
            onClick={() => navigate("/job-scanner")}
          >

            <div className="icon">💼</div>

            <h3>Job Scanner</h3>

            <p>
              Identify fake job postings and recruitment scams.
            </p>

            <span>Open →</span>

          </div>

          <div
            className="card"
            onClick={() => navigate("/message-scanner")}
          >

            <div className="icon">📧</div>

            <h3>Message Scanner</h3>

            <p>
              Scan suspicious emails and SMS messages for fraud.
            </p>

            <span>Open →</span>

          </div>

          <div
            className="card"
            onClick={() => navigate("/reports")}
          >

            <div className="icon">📋</div>

            <h3>Community Reports</h3>

            <p>
              Report scams and browse community-submitted threats.
            </p>

            <span>Open →</span>

          </div>

          <div
            className="card"
            onClick={() => navigate("/analytics")}
          >

            <div className="icon">📊</div>

            <h3>Analytics</h3>

            <p>
              View platform statistics and scam detection insights.
            </p>

            <span>Open →</span>

          </div>

          <div
            className="card"
            onClick={() => navigate("/admin")}
          >

            <div className="icon">👨‍💼</div>

            <h3>Admin Panel</h3>

            <p>
              Manage reports and monitor the ScamShield platform.
            </p>

            <span>Open →</span>

          </div>

        </div>

      </div>

    </div>
  );
}

export default Dashboard;