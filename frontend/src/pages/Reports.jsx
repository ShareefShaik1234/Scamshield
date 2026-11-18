import { useEffect, useState } from "react";
import api from "../services/api";
import "../styles/Reports.css";

function Reports() {

    const [reports, setReports] = useState([]);

    const [url, setUrl] = useState("");
    const [description, setDescription] = useState("");
    const [category, setCategory] = useState("");
    const [reportedBy, setReportedBy] = useState("");

    const [editId, setEditId] = useState(null);

    useEffect(() => {
        loadReports();
    }, []);

    const loadReports = async () => {

        try {

            const response = await api.get("/api/reports");

            setReports(response.data);

        } catch (error) {

            console.log(error);

            alert("Failed to load reports");

        }

    };

    const clearForm = () => {

        setUrl("");
        setDescription("");
        setCategory("");
        setReportedBy("");
        setEditId(null);

    };

    const handleSubmit = async () => {

        if (
            !url ||
            !description ||
            !category ||
            !reportedBy
        ) {

            alert("Please fill all fields");

            return;

        }

        const report = {

            url,
            description,
            category,
            reportedBy

        };

        try {

            if (editId == null) {

                await api.post("/api/reports", report);

                alert("Report Submitted Successfully");

            } else {

                await api.put(`/api/reports/${editId}`, report);

                alert("Report Updated Successfully");

            }

            clearForm();

            loadReports();

        } catch (error) {

            console.log(error);

            alert("Operation Failed");

        }

    };

    const editReport = (report) => {

        setEditId(report.id);

        setUrl(report.url);

        setDescription(report.description);

        setCategory(report.category);

        setReportedBy(report.reportedBy);

        window.scrollTo({
            top: 0,
            behavior: "smooth"
        });

    };

    const deleteReport = async (id) => {

        const confirmDelete = window.confirm(
            "Delete this report?"
        );

        if (!confirmDelete)
            return;

        try {

            await api.delete(`/api/reports/${id}`);

            alert("Deleted Successfully");

            loadReports();

        } catch (error) {

            console.log(error);

            alert("Delete Failed");

        }

    };

    return (

        <div className="reports-container">

            <div className="report-form">

                <h2>📋 Community Scam Reporting</h2>

<p className="subtitle">
Help protect others by reporting suspicious websites, emails, SMS, job offers, and other online scams.
</p>

                <input
                    type="text"
                    placeholder="Scam URL"
                    value={url}
                    onChange={(e) => setUrl(e.target.value)}
                />

                <textarea
                    rows="4"
                    placeholder="Description"
                    value={description}
                    onChange={(e) =>
                        setDescription(e.target.value)
                    }
                />

                <select
                    value={category}
                    onChange={(e) =>
                        setCategory(e.target.value)
                    }
                >

                    <option value="">
                        Select Category
                    </option>

                    <option>
                        URL Scam
                    </option>

                    <option>
                        Email Scam
                    </option>

                    <option>
                        SMS Scam
                    </option>

                    <option>
                        Job Scam
                    </option>

                    <option>
                        QR Scam
                    </option>

                    <option>
                        UPI Scam
                    </option>

                    <option>
                        Other
                    </option>

                </select>

                <input
                    type="text"
                    placeholder="Reported By"
                    value={reportedBy}
                    onChange={(e) =>
                        setReportedBy(e.target.value)
                    }
                />

                <button onClick={handleSubmit}>

                    {editId == null
                        ? "Submit Report"
                        : "Update Report"}

                </button>

            </div>

            <div className="reports-table">

                <h2>🛡 Community Reports</h2>

                <table>

                    <thead>

                        <tr>

                            <th>ID</th>

                            <th>Category</th>

                            <th>URL</th>

                            <th>Status</th>

                            <th>Reported By</th>

                            <th>Date</th>

                            <th>Action</th>

                        </tr>

                    </thead>

                    <tbody>
                      {
    reports.length === 0 ? (

        <tr>

            <td colSpan="7">

                No Reports Found

            </td>

        </tr>

    ) : (

        reports.map((report) => (

            <tr key={report.id}>

                <td>{report.id}</td>

                <td>{report.category}</td>

                <td>{report.url}</td>

                <td>{report.status}</td>

                <td>{report.reportedBy}</td>

                <td>

                    {new Date(
                        report.reportedDate
                    ).toLocaleString()}

                </td>

                <td>

                    <button
                        className="edit-btn"
                        onClick={() =>
                            editReport(report)
                        }
                    >
                        Edit
                    </button>

                    <button
                        className="delete-btn"
                        onClick={() =>
                            deleteReport(report.id)
                        }
                    >
                        Delete
                    </button>

                </td>

            </tr>

        ))

    )
}

                    </tbody>

                </table>

            </div>

        </div>

    );

}

export default Reports;