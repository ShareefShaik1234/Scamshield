import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import api from "../services/api";
import "../styles/Login.css";

function Login() {
  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async () => {
    if (!email || !password) {
      alert("Please enter email and password.");
      return;
    }

    try {
      const response = await api.post("/auth/login", {
        email,
        password,
      });

      // Save user information
      alert(response.data.message);

if (response.data.message === "Login Successful") {
  localStorage.setItem("user", JSON.stringify(response.data));
  navigate("/dashboard");
}
    } catch (error) {
      if (error.response) {
        if (typeof error.response.data === "string") {
          alert(error.response.data);
        } else {
          alert(error.response.data.message || "Login Failed");
        }
      } else {
        alert("Unable to connect to the server.");
      }
    }
  };

  return (
    <div className="login-container">
      <div className="left-panel">

    <h1>🛡 ScamShield</h1>

    <h2>AI Powered Universal Scam Detection Platform</h2>

    <div className="features">

        <div className="feature">
            <span className="feature-icon">🛡</span>
            <span>Detect Phishing URLs</span>
        </div>

        <div className="feature">
            <span className="feature-icon">💼</span>
            <span>Identify Fake Job Offers</span>
        </div>

        <div className="feature">
            <span className="feature-icon">📧</span>
            <span>Scan Email & SMS Scams</span>
        </div>

        <div className="feature">
            <span className="feature-icon">📢</span>
            <span>Community Scam Reporting</span>
        </div>

        <div className="feature">
            <span className="feature-icon">📊</span>
            <span>Real-Time Analytics</span>
        </div>

    </div>

</div>

      <div className="right-panel">
        <div className="login-box">

          <h2>Welcome Back</h2>

          <input
            type="email"
            placeholder="Email Address"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />

          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />

          <button onClick={handleLogin}>
            Login
          </button>

          <p>
            Don't have an account?{" "}
            <Link to="/register" className="register-link">
              Register
            </Link>
          </p>

        </div>
      </div>
    </div>
  );
}

export default Login;