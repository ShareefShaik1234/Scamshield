import { Routes, Route } from "react-router-dom";

import Login from "./pages/Login";
import Register from "./pages/Register";
import Dashboard from "./pages/Dashboard";
import UrlScanner from "./pages/UrlScanner";
import JobScanner from "./pages/JobScanner";
import MessageScanner from "./pages/MessageScanner";
import Reports from "./pages/Reports";
import Analytics from "./pages/Analytics";
import Admin from "./pages/Admin";

import NotFound from "./pages/NotFound";

function App() {
  return (
    <Routes>
      <Route path="/" element={<Login />} />
      <Route path="/register" element={<Register />} />
      <Route path="/dashboard" element={<Dashboard />} />
      <Route path="/url-scanner" element={<UrlScanner />} />
      <Route path="/job-scanner" element={<JobScanner />} />
      <Route path="/message-scanner" element={<MessageScanner />} />
      <Route path="/reports" element={<Reports />} />
      <Route path="/analytics" element={<Analytics />} />
      <Route path="/admin" element={<Admin />} />
      <Route path="*" element={<NotFound />} />
    </Routes>
  );
}

export default App;