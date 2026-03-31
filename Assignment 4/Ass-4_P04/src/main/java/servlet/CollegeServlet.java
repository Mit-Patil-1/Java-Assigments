package servlet;

import ejb.CollegeBean;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/college")
public class CollegeServlet extends HttpServlet {

    @EJB
    private CollegeBean bean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("students", bean.allStudents());
        req.setAttribute("subjects", bean.allSubjects());
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("addStudent".equals(action)) {
            bean.addStudent(req.getParameter("studentName"));
        } else if ("addSubject".equals(action)) {
            bean.addSubject(req.getParameter("subjectName"));
        } else if ("assign".equals(action)) {
            long studentId = Long.parseLong(req.getParameter("studentId"));
            long subjectId = Long.parseLong(req.getParameter("subjectId"));
            boolean ok = bean.assignSubject(studentId, subjectId);
            req.setAttribute("message", ok ? "Assignment done" : "Invalid student or subject id");
        }
        doGet(req, resp);
    }
}

