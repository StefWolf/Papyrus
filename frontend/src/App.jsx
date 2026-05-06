import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Login from "./pages/Login";
import Navbar from "./components/Navbar";
import Artigos from "./pages/Artigos";
import Autores from "./pages/Autores";
import Areas from "./pages/Areas";
import Periodicos from "./pages/Periodicos";

function App() {
  const isAuth = !!localStorage.getItem("token");

  return (
    <BrowserRouter>
      <Routes>

        {/* Login - tudin pode ver :p */}
        <Route
          path="/login"
          element={!isAuth ? <Login /> : <Navigate to="/artigos" />}
        />

        {/* Área protegida - somente users autenticados */}
        <Route
          path="/*"
          element={
            isAuth ? (
              <div>
                <Navbar />
                <Routes>
                  <Route path="/artigos" element={<Artigos />} />
                  <Route path="/autores" element={<Autores />} />
                  <Route path="/areas" element={<Areas />} />
                  <Route path="/periodicos" element={<Periodicos />} />
                </Routes>
              </div>
            ) : (
              <Navigate to="/login" />
            )
          }
        />

      </Routes>
    </BrowserRouter>
  );
}

export default App;