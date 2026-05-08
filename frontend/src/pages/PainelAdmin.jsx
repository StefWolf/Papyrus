import { useEffect, useState } from "react";
import axios from "axios";

export default function PainelAdmin() {
  const [alunos, setAlunos] = useState([]);
  const [professores, setProfessores] = useState([]);
  const [erro, setErro] = useState("");
  const [carregando, setCarregando] = useState(true);

  const token = localStorage.getItem("token");

  const api = axios.create({
    baseURL: "http://localhost:8080",
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });

  async function carregarDados() {
    try {
      setCarregando(true);
      setErro("");

      const alunosResponse = await api.get("/alunos");
      const professoresResponse = await api.get("/professores");

      setAlunos(alunosResponse.data);
      setProfessores(professoresResponse.data);
    } catch (error) {
      setErro("Erro ao carregar dados do painel administrativo.");
    } finally {
      setCarregando(false);
    }
  }

  async function deletarProfessor(id) {
    const confirmar = window.confirm("Deseja remover este professor?");

    if (!confirmar) return;

    try {
      await api.delete(`/professores/${id}`);
      carregarDados();
    } catch (error) {
      setErro("Erro ao remover professor.");
    }
  }

  async function finalizarAluno(id) {
    const confirmar = window.confirm("Deseja finalizar este aluno?");

    if (!confirmar) return;

    try {
      await api.patch(`/alunos/${id}/finalizar`);
      carregarDados();
    } catch (error) {
      setErro("Erro ao finalizar aluno.");
    }
  }

  useEffect(() => {
    carregarDados();
  }, []);

  if (carregando) {
    return (
      <main className="p-8">
        <p className="text-slate-600">Carregando painel...</p>
      </main>
    );
  }

  return (
    <main className="min-h-screen bg-slate-100 p-8">
      <div className="max-w-7xl mx-auto">
        <h1 className="text-3xl font-bold text-slate-800">
          Painel do Administrador
        </h1>

        <p className="text-slate-500 mt-2">
          Escolha uma opção no menu para gerenciar alunos ou professores.
        </p>

        <div className="grid grid-cols-1 md:grid-cols-2 gap-6 mt-8">
          <div className="bg-white rounded-2xl shadow p-6">
            <h2 className="text-xl font-bold text-slate-800">
              Alunos
            </h2>
            <p className="text-slate-500 mt-2">
              Consulte alunos, veja status e finalize acompanhamentos.
            </p>
          </div>

          <div className="bg-white rounded-2xl shadow p-6">
            <h2 className="text-xl font-bold text-slate-800">
              Professores
            </h2>
            <p className="text-slate-500 mt-2">
              Consulte e gerencie professores cadastrados no sistema.
            </p>
          </div>
        </div>
      </div>
    </main>
  );

}