import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { getUserRole } from "../utils/auth";

export default function Login({ onLoginSuccess }) {
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");
  const [erro, setErro] = useState("");
  const [carregando, setCarregando] = useState(false);

  const navigate = useNavigate();

  function getRoleFromToken(token) {
  try {
    const payload = JSON.parse(atob(token.split(".")[1]));

    const roles = payload.roles;

    if (!roles) return null;

    const rolesText = JSON.stringify(roles);

    if (rolesText.includes("ROLE_ADM")) return "ROLE_ADM";
    if (rolesText.includes("ROLE_PROFESSOR")) return "ROLE_PROFESSOR";
    if (rolesText.includes("ROLE_ALUNO")) return "ROLE_ALUNO";

    return null;
  } catch (error) {
    return null;
  }
}

  async function handleLogin(e) {
  e.preventDefault();
  setErro("");
  setCarregando(true);

  try {
    const response = await axios.post("http://localhost:8080/auth/login", {
      email,
      senha,
    });

    const token = response.data.token;

    localStorage.setItem("token", token);

    const role = getRoleFromToken(token);

    console.log("TOKEN:", token);
    console.log("ROLE:", role);

    onLoginSuccess(token);

    if (role === "ROLE_ALUNO") {
      navigate("/artigos", { replace: true });
    } else if (role === "ROLE_PROFESSOR") {
      navigate("/painel-professor", { replace: true });
    } else if (role === "ROLE_ADM") {
      navigate("/painel-admin", { replace: true });
    } else {
      setErro("Não foi possível identificar a permissão do usuário.");
    }

  } catch (error) {
    setErro("Email ou senha inválidos.");
  } finally {
    setCarregando(false);
  }
}

  return (
    <div className="min-h-screen flex items-center justify-center bg-slate-100 px-4">
      <div className="w-full max-w-md bg-white rounded-2xl shadow-lg p-8">
        <div className="mb-8 text-center">
          <h1 className="text-3xl font-bold text-slate-800">Papyus</h1>
          <p className="text-slate-500 mt-2">
            Sua melhor opção para organizar suas pesquisas! Acesse sua conta para continuar
          </p>
        </div>

        <form onSubmit={handleLogin} className="space-y-5">
          <div>
            <label className="block text-left text-md font-medium text-slate-700 mb-1">
              Email do Usuário
            </label>
            <input
              type="email"
              placeholder="adm@email.com"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              className="w-full rounded-lg border border-slate-300 px-4 py-3 outline-none focus:ring-2 focus:ring-blue-500"
              required
            />
          </div>

          <div>
            <label className="block text-left text-md font-medium text-slate-700 mb-1">
              Senha do Usuário
            </label>
            <input
              type="password"
              placeholder="Digite sua senha"
              value={senha}
              onChange={(e) => setSenha(e.target.value)}
              className="w-full rounded-lg border border-slate-300 px-4 py-3 outline-none focus:ring-2 focus:ring-blue-500"
              required
            />
          </div>

          {erro && (
            <div className="rounded-lg bg-red-100 text-red-700 px-4 py-3 text-sm">
              {erro}
            </div>
          )}

          <button
            type="submit"
            disabled={carregando}
            className="w-full bg-blue-600 hover:bg-blue-700 disabled:bg-blue-300 text-white font-semibold py-3 rounded-lg transition"
          >
            {carregando ? "Entrando..." : "Entrar"}
          </button>
        </form>
      </div>
    </div>
  );
}