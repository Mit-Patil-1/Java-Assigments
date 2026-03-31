<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="entity.Student"%>
<%@page import="entity.Subject"%>
<!DOCTYPE html>
<html>
<head>
    <title>Ass-4_P04 College Subject Mapping</title>
    <meta charset="UTF-8"/>
</head>
<body>
<h2>College Subject-Student Mapping (Many-to-Many)</h2>
<form method="post" action="college">
    <input type="hidden" name="action" value="addStudent"/>
    <label>Student Name:</label>
    <input type="text" name="studentName" required/>
    <button type="submit">Add Student</button>
</form>

<form method="post" action="college" style="margin-top:8px;">
    <input type="hidden" name="action" value="addSubject"/>
    <label>Subject Name:</label>
    <input type="text" name="subjectName" required/>
    <button type="submit">Add Subject</button>
</form>

<form method="post" action="college" style="margin-top:8px;">
    <input type="hidden" name="action" value="assign"/>
    <label>Student ID:</label>
    <input type="number" name="studentId" required/>
    <label>Subject ID:</label>
    <input type="number" name="subjectId" required/>
    <button type="submit">Assign Subject</button>
</form>

<p><b><%= request.getAttribute("message") == null ? "" : request.getAttribute("message") %></b></p>

<h3>Studentwise Subjects</h3>
<%
    List<Student> students = (List<Student>) request.getAttribute("students");
    if (students != null) {
        for (Student s : students) {
%>
<p><b><%= s.getId() %> - <%= s.getStudentName() %></b>:
<%
            for (Subject sub : s.getSubjects()) {
                out.print(sub.getSubjectName() + " ");
            }
%>
</p>
<%
        }
    }
%>

<h3>Subjectwise Students</h3>
<%
    List<Subject> subjects = (List<Subject>) request.getAttribute("subjects");
    if (subjects != null) {
        for (Subject s : subjects) {
%>
<p><b><%= s.getId() %> - <%= s.getSubjectName() %></b>:
<%
            for (Student st : s.getStudents()) {
                out.print(st.getStudentName() + " ");
            }
%>
</p>
<%
        }
    }
%>
</body>
</html>

