import { useEffect, useState } from "react";
import api from "../services/api";
import "../styles/Admin.css";

function Admin() {

    const [dashboard, setDashboard] = useState({});
    const [pendingReports, setPendingReports] = useState([]);
    const [resolvedReports, setResolvedReports] = useState([]);

    useEffect(() => {
        loadDashboard();
        loadPendingReports();
        loadResolvedReports();
    }, []);

    const loadDashboard = async () => {
        try {
            const response = await api.get("/api/admin/dashboard");
            setDashboard(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    const loadPendingReports = async () => {
        try {
            const response = await api.get("/api/admin/pending");
            setPendingReports(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    const loadResolvedReports = async () => {
        try {
            const response = await api.get("/api/admin/resolved");
            setResolvedReports(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    return (

        <div className="admin-container">

            <div className="header">

                <h1>👨‍💼 Admin Control Center</h1>

                <p>
                    Manage community reports and monitor ScamShield platform activity.
                </p>

            </div>

            <div className="stats">

                <div className="stat-card">
                    <h3>📋 Total Reports</h3>
                    <h2>{dashboard.totalReports}</h2>
                </div>

                <div className="stat-card pending">
                    <h3>🟡 Pending</h3>
                    <h2>{dashboard.pendingReports}</h2>
                </div>

                <div className="stat-card resolved">
                    <h3>🟢 Resolved</h3>
                    <h2>{dashboard.resolvedReports}</h2>
                </div>

                <div className="stat-card rejected">
                    <h3>🔴 Rejected</h3>
                    <h2>{dashboard.rejectedReports}</h2>
                </div>

            </div>

            <div className="table-card">

                <h2>🟡 Pending Reports</h2>

                <table>

                    <thead>

                        <tr>

                            <th>ID</th>
                            <th>Category</th>
                            <th>Reported By</th>
                            <th>Status</th>

                        </tr>

                    </thead>

                    <tbody>

                        {pendingReports.length === 0 ?

                            <tr>

                                <td colSpan="4">
                                    No Pending Reports
                                </td>

                            </tr>

                            :

                            pendingReports.map(report => (

                                <tr key={report.id}>

                                    <td>{report.id}</td>
                                    <td>{report.category}</td>
                                    <td>{report.reportedBy}</td>
                                    <td>{report.status}</td>

                                </tr>

                            ))

                        }

                    </tbody>

                </table>

            </div>

            <div className="table-card">

                <h2>🟢 Resolved Reports</h2>

                <table>

                    <thead>

                        <tr>

                            <th>ID</th>
                            <th>Category</th>
                            <th>Reported By</th>
                            <th>Status</th>

                        </tr>

                    </thead>

                    <tbody>

                        {resolvedReports.length === 0 ?

                            <tr>

                                <td colSpan="4">
                                    No Resolved Reports
                                </td>

                            </tr>

                            :

                            resolvedReports.map(report => (

                                <tr key={report.id}>

                                    <td>{report.id}</td>
                                    <td>{report.category}</td>
                                    <td>{report.reportedBy}</td>
                                    <td>{report.status}</td>

                                </tr>

                            ))

                        }

                    </tbody>

                </table>

            </div>

        </div>

    );

}

export default Admin;