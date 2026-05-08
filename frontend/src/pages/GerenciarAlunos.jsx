import { useEffect, useState } from "react";
import axios from "axios";

export default function GerenciarAlunos() {
  const [alunos, setAlunos] = useState([]);
  const [erro, setErro] = useState("");
  const [carregando, setCarregando] = useState(true);

  const api = axios.create({
    baseURL: "http://localhost:8080",
    headers: {
      Authorization: `Bearer ${localStorage.getItem("token")}`,
    },
  });

  async function carregarAlunos() {
    try {
      setCarregando(true);
      const response = await api.get("/alunos");
      setAlunos(response.data);
    } catch (error) {
      setErro("Erro ao carregar alunos.");
    } finally {
      setCarregando(false);
    }
  }

  async function finalizarAluno(id) {
    if (!window.confirm("Deseja finalizar este aluno?")) return;

    try {
      await api.patch(`/alunos/${id}/finalizar`);
      carregarAlunos();
    } catch (error) {
      setErro("Erro ao finalizar aluno.");
    }
  }

  useEffect(() => {
    carregarAlunos();
  }, []);

  const [novoAluno, setNovoAluno] = useState({
  nome: "",
  email: "",
  senha: "",
  numeroMatricula: "",
  semestre: "",
  curso: "",
});

async function cadastrarAluno(e) {
  e.preventDefault();

  try {
    await api.post("/alunos", {
      ...novoAluno,
      semestre: Number(novoAluno.semestre),
    });

    setNovoAluno({
      nome: "",
      email: "",
      senha: "",
      numeroMatricula: "",
      semestre: "",
      curso: "",
    });

    carregarAlunos();
  } catch (error) {
    setErro("Erro ao cadastrar aluno.");
  }
}

  return (
    <main className="min-h-screen bg-slate-100 p-8">
      <div className="max-w-7xl mx-auto bg-white rounded-2xl shadow p-6">
        <h1 className="text-2xl font-bold text-slate-800">
          Gerenciar Alunos
        </h1>

        <p className="text-slate-500 mt-1 mb-6">
          Consulte, acompanhe e finalize alunos cadastrados.
        </p>

        <form onSubmit={cadastrarAluno} className="grid grid-cols-1 md:grid-cols-3 gap-4 mb-6">
  <input
    placeholder="Nome"
    value={novoAluno.nome}
    onChange={(e) => setNovoAluno({ ...novoAluno, nome: e.target.value })}
    className="border rounded-lg px-4 py-2"
    required
  />

  <input
    placeholder="Email"
    type="email"
    value={novoAluno.email}
    onChange={(e) => setNovoAluno({ ...novoAluno, email: e.target.value })}
    className="border rounded-lg px-4 py-2"
    required
  />

  <input
    placeholder="Senha"
    type="password"
    value={novoAluno.senha}
    onChange={(e) => setNovoAluno({ ...novoAluno, senha: e.target.value })}
    className="border rounded-lg px-4 py-2"
    required
  />

  <input
    placeholder="Matrícula"
    value={novoAluno.numeroMatricula}
    onChange={(e) =>
      setNovoAluno({ ...novoAluno, numeroMatricula: e.target.value })
    }
    className="border rounded-lg px-4 py-2"
    required
  />

  <input
    placeholder="Semestre"
    type="number"
    value={novoAluno.semestre}
    onChange={(e) => setNovoAluno({ ...novoAluno, semestre: e.target.value })}
    className="border rounded-lg px-4 py-2"
    required
  />

  <input
    placeholder="Curso"
    value={novoAluno.curso}
    onChange={(e) => setNovoAluno({ ...novoAluno, curso: e.target.value })}
    className="border rounded-lg px-4 py-2"
    required
  />

  <button
    type="submit"
    className="md:col-span-3 bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-lg"
  >
    Cadastrar Aluno
  </button>
</form>

        {erro && (
          <div className="mb-4 bg-red-100 text-red-700 px-4 py-3 rounded-xl">
            {erro}
          </div>
        )}

        {carregando ? (
          <p>Carregando alunos...</p>
        ) : (
          <div className="overflow-x-auto">
            <table className="w-full text-left">
              <thead>
                <tr className="border-b text-slate-600">
                  <th className="py-3 px-4">Nome</th>
                  <th className="py-3 px-4">Email</th>
                  <th className="py-3 px-4">Matrícula</th>
                  <th className="py-3 px-4">Curso</th>
                  <th className="py-3 px-4">Semestre</th>
                  <th className="py-3 px-4">Status</th>
                  <th className="py-3 px-4">Ações</th>
                </tr>
              </thead>

              <tbody>
                {alunos.map((aluno) => (
                  <tr key={aluno.id} className="border-b hover:bg-slate-50">
                    <td className="py-3 px-4">{aluno.nome}</td>
                    <td className="py-3 px-4">{aluno.email}</td>
                    <td className="py-3 px-4">{aluno.numeroMatricula}</td>
                    <td className="py-3 px-4">{aluno.curso}</td>
                    <td className="py-3 px-4">{aluno.semestre}</td>
                    <td className="py-3 px-4">{aluno.status}</td>
                    <td className="py-3 px-4 flex gap-2">
                      <button
                        onClick={() => finalizarAluno(aluno.id)}
                        className="bg-emerald-500 hover:bg-emerald-600 text-white px-3 py-2 rounded-lg text-sm"
                      >
                        Finalizar
                      </button>

                      <button
                        onClick={() => alert("Depois ligamos com progresso")}
                        className="bg-blue-500 hover:bg-blue-600 text-white px-3 py-2 rounded-lg text-sm"
                      >
                        Progresso
                      </button>
                    </td>
                  </tr>
                ))}

                {alunos.length === 0 && (
                  <tr>
                    <td colSpan="7" className="py-6 text-center text-slate-500">
                      Nenhum aluno cadastrado.
                    </td>
                  </tr>
                )}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </main>
  );
}