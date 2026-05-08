import { useState } from "react";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";

import Login from "./pages/Login";
import Navbar from "./components/Navbar";

import Artigos from "./pages/Artigos";
import Autores from "./pages/Autores";
import Areas from "./pages/Areas";
import Periodicos from "./pages/Periodicos";
import PainelProfessor from "./pages/PainelProfessor";
import PainelAdmin from "./pages/PainelAdmin";
import GerenciarAlunos from "./pages/GerenciarAlunos";
import GerenciarProfessores from "./pages/GerenciarProfessores";

import { getToken, getUserRole } from "./utils/auth";

function getHomeByRole(role) {
  if (role === "ROLE_ALUNO") return "/artigos";
  if (role === "ROLE_PROFESSOR") return "/painel-professor";
  if (role === "ROLE_ADM") return "/painel-admin";
  return "/login";
}

function PrivateRoute({ children, allowedRoles }) {
  const token = getToken();
  const role = getUserRole();

  if (!token) {
    return <Navigate to="/login" />;
  }

  if (allowedRoles && !allowedRoles.includes(role)) {
    return <Navigate to={getHomeByRole(role)} />;
  }

  return children;
}

function AppLayout({ onLogout }) {
  return (
    <div>
      <Navbar onLogout={onLogout} />

      <Routes>
        <Route
          path="/artigos"
          element={
            <PrivateRoute allowedRoles={["ROLE_ALUNO"]}>
              <Artigos />
            </PrivateRoute>
          }
        />

        <Route
          path="/autores"
          element={
            <PrivateRoute allowedRoles={["ROLE_ALUNO"]}>
              <Autores />
            </PrivateRoute>
          }
        />

        <Route
          path="/areas"
          element={
            <PrivateRoute allowedRoles={["ROLE_ALUNO"]}>
              <Areas />
            </PrivateRoute>
          }
        />

        <Route
          path="/periodicos"
          element={
            <PrivateRoute allowedRoles={["ROLE_ALUNO"]}>
              <Periodicos />
            </PrivateRoute>
          }
        />

        <Route
          path="/painel-professor"
          element={
            <PrivateRoute allowedRoles={["ROLE_PROFESSOR"]}>
              <PainelProfessor />
            </PrivateRoute>
          }
        />

        <Route
          path="/painel-admin"
          element={
            <PrivateRoute allowedRoles={["ROLE_ADM"]}>
              <PainelAdmin />
            </PrivateRoute>
          }
        />

        <Route
          path="/alunos"
          element={
            <PrivateRoute allowedRoles={["ROLE_PROFESSOR", "ROLE_ADM"]}>
              <GerenciarAlunos />
            </PrivateRoute>
          }
        />

        <Route
          path="/professores"
          element={
            <PrivateRoute allowedRoles={["ROLE_ADM"]}>
              <GerenciarProfessores />
            </PrivateRoute>
          }
        />

        <Route
          path="*"
          element={<Navigate to={getHomeByRole(getUserRole())} />}
        />
      </Routes>
    </div>
  );
}

export default function App() {
  const [token, setToken] = useState(getToken());

  function handleLoginSuccess(newToken) {
    setToken(newToken);
  }

  function handleLogout() {
    localStorage.removeItem("token");
    setToken(null);
  }

  const role = getUserRole();

  return (
    <BrowserRouter>
      <Routes>
        <Route
          path="/login"
          element={
            token ? (
              <Navigate to={getHomeByRole(role)} />
            ) : (
              <Login onLoginSuccess={handleLoginSuccess} />
            )
          }
        />

        <Route
          path="/*"
          element={
            token ? (
              <AppLayout onLogout={handleLogout} />
            ) : (
              <Navigate to="/login" />
            )
          }
        />
      </Routes>
    </BrowserRouter>
  );
}