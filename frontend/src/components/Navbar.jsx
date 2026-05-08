import { useNavigate, useLocation } from "react-router-dom";
import { getUserRole, logout } from "../utils/auth";

export default function Navbar() {
  const navigate = useNavigate();
  const location = useLocation();

  const role = getUserRole();

  function sair() {
    logout();
    navigate("/login");
  }

  function navItemClass(path) {
    return `
      px-4 py-2 rounded-xl text-sm font-medium transition-all duration-200
      ${
        location.pathname === path
          ? "bg-blue-600 text-white shadow-md"
          : "text-slate-700 hover:bg-slate-100"
      }
    `;
  }

  return (
    <nav className="w-full bg-blue-600 border-b border-slate-200 shadow-sm px-6 py-4">
      <div className="max-w-7xl mx-auto flex items-center justify-between">
        <div className="flex items-center gap-3">
          <div className="w-10 h-10 rounded-xl bg-black-600 flex items-center justify-center text-white font-bold text-lg shadow-md">
            
          </div>

          <div>
            <h1 className="text-xl text-white font-bold text-slate-500">Papyus</h1>
           
          </div>
        </div>

        <div className="flex items-center gap-2">
          {role === "ROLE_ALUNO" && (
            <>
              <button onClick={() => navigate("/artigos")} className={navItemClass("/artigos")}>
                Artigos
              </button>

              <button onClick={() => navigate("/autores")} className={navItemClass("/autores")}>
                Autores
              </button>

              <button onClick={() => navigate("/areas")} className={navItemClass("/areas")}>
                Áreas
              </button>

              <button onClick={() => navigate("/periodicos")} className={navItemClass("/periodicos")}>
                Periódicos
              </button>
            </>
          )}

          {role === "ROLE_PROFESSOR" && (
            <>
              <button
                onClick={() => navigate("/painel-professor")}
                className={navItemClass("/painel-professor")}
              >
                Painel Professor
              </button>

              <button
                onClick={() => navigate("/alunos")}
                className={navItemClass("/alunos")}
              >
                Gerenciar Alunos
              </button>
            </>
          )}

          {role === "ROLE_ADM" && (
            <>
              <button
                onClick={() => navigate("/painel-admin")}
                className={navItemClass("/painel-admin")}
              >
                Painel Admin
              </button>

              <button
                onClick={() => navigate("/alunos")}
                className={navItemClass("/alunos")}
              >
                Alunos
              </button>

              <button
                onClick={() => navigate("/professores")}
                className={navItemClass("/professores")}
              >
                Professores
              </button>
            </>
          )}

          <div className="w-px h-6 bg-slate-300 mx-2" />

          <button
            onClick={sair}
            className="px-4 py-2 rounded-xl text-sm font-medium bg-red-500 text-white hover:bg-red-600 transition-all duration-200 shadow-sm"
          >
            Sair
          </button>
        </div>
      </div>
    </nav>
  );
}