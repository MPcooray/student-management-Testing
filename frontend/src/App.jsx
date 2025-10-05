import { useEffect, useState } from "react";
import "./App.css";

const API_BASE = import.meta.env.VITE_API_BASE_URL || "http://localhost:8080";
const GRADES = ["A","B","C","D","E","F"];

export default function App() {
  const [students, setStudents] = useState([]);
  const [name, setName] = useState("");
  const [grade, setGrade] = useState("A");
  const [filterGrade, setFilterGrade] = useState("ALL");
  const [err, setErr] = useState("");

  async function load(currentFilter = filterGrade) {
    try {
      const qs = currentFilter === "ALL" ? "" : `?grade=${currentFilter}`;
      const res = await fetch(`${API_BASE}/students${qs}`);
      const data = await res.json();
      console.log("Loaded students:", data); 
      setStudents(data);
    } catch {
      setErr("Failed to load students");
    }
  }

  useEffect(() => { load(); }, []);

  async function addStudent(e) {
    e.preventDefault();
    setErr("");
    const res = await fetch(`${API_BASE}/students`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ name, grade })
    });
    if (!res.ok) { setErr("Validation failed (name required, grade A–F)"); return; }
    setName(""); setGrade("A");
    await load();
  }

  async function deleteStudent(id, name) {
    if (!confirm(`Are you sure you want to delete ${name}?`)) return;
    setErr("");
    const res = await fetch(`${API_BASE}/students/${id}`, { method: "DELETE" });
    if (!res.ok) { setErr("Delete failed"); return; }
    await load();
  }

  async function updateGrade(id, newGrade) {
    setErr("");
    const res = await fetch(`${API_BASE}/students/${id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ grade: newGrade })
    });
    if (!res.ok) { setErr("Update failed (grade A–F)"); return; }
    await load();
  }

  const filteredCount = filterGrade === "ALL" ? students.length : students.filter(s => s.grade === filterGrade).length;

  return (
    <div className="app-container">
      <div className="app-card">
        <h1 className="app-title">🎓 Student Management System</h1>

        {/* Filter Section */}
        <div className="filter-container">
          <h3 className="filter-title">
            🔍 Filter Students
          </h3>
          <div className="filter-row">
            <label className="filter-label">Show Grade:</label>
            <select 
              className="filter-select"
              value={filterGrade} 
              onChange={e => { setFilterGrade(e.target.value); load(e.target.value); }}
            >
              <option value="ALL">All Grades ({students.length} students)</option>
              {GRADES.map(g => <option key={g} value={g}>Grade {g}</option>)}
            </select>
          </div>
        </div>

        {/* Add Student Form */}
        <div className="form-container">
          <h2 className="form-title">
            ➕ Add New Student
          </h2>
          <form onSubmit={addStudent}>
            <div className="form-row">
              <div className="form-group">
                <label className="form-label">Student Name</label>
                <input
                  className="form-input"
                  placeholder="Enter student name"
                  value={name}
                  onChange={e => setName(e.target.value)}
                  required
                />
              </div>
              <div className="form-group">
                <label className="form-label">Grade</label>
                <select 
                  className="form-select"
                  value={grade} 
                  onChange={e => setGrade(e.target.value)}
                >
                  {GRADES.map(g => 
                    <option key={g} value={g}>Grade {g}</option>
                  )}
                </select>
              </div>
              <button type="submit" className="btn-primary">
                Add Student
              </button>
            </div>
          </form>
        </div>

        {/* Error Message */}
        {err && (
          <div className="error-message">
            ⚠️ {err}
          </div>
        )}

        {/* Students List */}
        <div className="students-section">
          <h2 className="section-title">
            👥 Students List 
            {filterGrade === "ALL" ? 
              `(${students.length})` : 
              `(${filteredCount} Grade ${filterGrade} students)`
            }
          </h2>
          
          <div className="students-table-container">
            <table className="students-table">
              <thead className="table-header">
                <tr>
                  <th style={{width: "80px"}}>ID</th>
                  <th>Name</th>
                  <th style={{width: "140px"}}>Grade</th>
                  <th style={{width: "120px"}}>Actions</th>
                </tr>
              </thead>
              <tbody>
                {students.map(s => (
                  <tr key={s.id} className="table-row">
                    <td className="table-cell">
                      <span className="student-id">#{s.id}</span>
                    </td>
                    <td className="table-cell">
                      <span className="student-name">{s.name}</span>
                    </td>
                    <td className="table-cell">
                      <div className="grade-display">
                        <span className={`grade-badge grade-${s.grade}`}>
                          Grade {s.grade}
                        </span>
                        <select
                          className="grade-select-small"
                          value={s.grade || "A"}
                          onChange={e => updateGrade(s.id, e.target.value)}
                          title={`Change grade from ${s.grade}`}
                        >
                          {GRADES.map(g => 
                            <option key={g} value={g}>{g}</option>
                          )}
                        </select>
                      </div>
                    </td>
                    <td className="table-cell">
                      <button 
                        className="btn-danger"
                        onClick={() => deleteStudent(s.id, s.name)}
                      >
                        🗑️ Delete
                      </button>
                    </td>
                  </tr>
                ))}
                {students.length === 0 && (
                  <tr>
                    <td colSpan="4" className="table-cell">
                      <div className="empty-state">
                        <div className="empty-state-icon">📚</div>
                        <div>
                          {filterGrade === "ALL" ? 
                            "No students added yet. Start by adding your first student!" :
                            `No students with grade ${filterGrade} found.`
                          }
                        </div>
                      </div>
                    </td>
                  </tr>
                )}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  );
}
