import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import api from "../services/api";
import "../styles/Register.css";

function Register() {
  const navigate = useNavigate();

  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleRegister = async () => {
    if (!name || !email || !password) {
      alert("Please fill in all fields.");
      return;
    }

    try {
      const response = await api.post("/auth/register", {
        name,
        email,
        password,
      });

      alert(response.data);

      // Clear form
      setName("");
      setEmail("");
      setPassword("");

      // Redirect to login page
      navigate("/");
    } catch (error) {
      if (error.response) {
        alert(error.response.data);
      } else {
        alert("Registration failed. Please try again.");
      }
    }
  };

  return (
    <div className="register-container">
      <div className="register-left">

    <h1>🛡 ScamShield</h1>

    <h2>Create Your Account</h2>

    <div className="features">

        <div className="feature">
            <span className="feature-icon">🛡</span>
            <span>Secure User Registration</span>
        </div>

        <div className="feature">
            <span className="feature-icon">🌐</span>
            <span>Detect Phishing Websites</span>
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
            <span className="feature-icon">📊</span>
            <span>Real-Time Threat Analytics</span>
        </div>

    </div>

</div>

      <div className="register-right">
        <div className="register-box">
          <h2>Register</h2>

          <input
            type="text"
            placeholder="Full Name"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />

          <input
            type="email"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />

          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />

          <button onClick={() => handleRegister()}>
  Register
</button>

          <p>
            Already have an account?{" "}
            <Link to="/" className="login-link">
    Login
</Link>
          </p>
        </div>
      </div>
    </div>
  );
}

export default Register;