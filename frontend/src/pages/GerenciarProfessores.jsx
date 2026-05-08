import { useEffect, useState } from "react";
import axios from "axios";

export default function GerenciarProfessores() {
  const [professores, setProfessores] = useState([]);
  const [erro, setErro] = useState("");
  const [carregando, setCarregando] = useState(true);

  const api = axios.create({
    baseURL: "http://localhost:8080",
    headers: {
      Authorization: `Bearer ${localStorage.getItem("token")}`,
    },
  });

  async function carregarProfessores() {
    try {
      setCarregando(true);
      const response = await api.get("/professores");
      setProfessores(response.data);
    } catch (error) {
      setErro("Erro ao carregar professores.");
    } finally {
      setCarregando(false);
    }
  }

  async function removerProfessor(id) {
    if (!window.confirm("Deseja remover este professor?")) return;

    try {
      await api.delete(`/professores/${id}`);
      carregarProfessores();
    } catch (error) {
      setErro("Erro ao remover professor.");
    }
  }

  useEffect(() => {
    carregarProfessores();
  }, []);

  const [novoProfessor, setNovoProfessor] = useState({
  nome: "",
  email: "",
  senha: "",
  formacao: "",
});

async function cadastrarProfessor(e) {
  e.preventDefault();

  try {
    await api.post("/professores", novoProfessor);

    setNovoProfessor({
      nome: "",
      email: "",
      senha: "",
      formacao: "",
    });

    carregarProfessores();
  } catch (error) {
    setErro("Erro ao cadastrar professor.");
  }
}

  return (
    <main className="min-h-screen bg-slate-100 p-8">
      <div className="max-w-7xl mx-auto bg-white rounded-2xl shadow p-6">
        <h1 className="text-2xl font-bold text-slate-800">
          Gerenciar Professores
        </h1>

        <p className="text-slate-500 mt-1 mb-6">
          Consulte e remova professores cadastrados.
        </p>

        <form onSubmit={cadastrarProfessor} className="grid grid-cols-1 md:grid-cols-4 gap-4 mb-6">
  <input
    placeholder="Nome"
    value={novoProfessor.nome}
    onChange={(e) =>
      setNovoProfessor({ ...novoProfessor, nome: e.target.value })
    }
    className="border rounded-lg px-4 py-2"
    required
  />

  <input
    placeholder="Email"
    type="email"
    value={novoProfessor.email}
    onChange={(e) =>
      setNovoProfessor({ ...novoProfessor, email: e.target.value })
    }
    className="border rounded-lg px-4 py-2"
    required
  />

  <input
    placeholder="Senha"
    type="password"
    value={novoProfessor.senha}
    onChange={(e) =>
      setNovoProfessor({ ...novoProfessor, senha: e.target.value })
    }
    className="border rounded-lg px-4 py-2"
    required
  />

  <input
    placeholder="Formação"
    value={novoProfessor.formacao}
    onChange={(e) =>
      setNovoProfessor({ ...novoProfessor, formacao: e.target.value })
    }
    className="border rounded-lg px-4 py-2"
    required
  />

  <button
    type="submit"
    className="md:col-span-4 bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-lg"
  >
    Cadastrar Professor
  </button>
</form>

        {erro && (
          <div className="mb-4 bg-red-100 text-red-700 px-4 py-3 rounded-xl">
            {erro}
          </div>
        )}

        {carregando ? (
          <p>Carregando professores...</p>
        ) : (
          <div className="overflow-x-auto">
            <table className="w-full text-left">
              <thead>
                <tr className="border-b text-slate-600">
                  <th className="py-3 px-4">Nome</th>
                  <th className="py-3 px-4">Email</th>
                  <th className="py-3 px-4">Formação</th>
                  <th className="py-3 px-4">Ações</th>
                </tr>
              </thead>

              <tbody>
                {professores.map((professor) => (
                  <tr key={professor.id} className="border-b hover:bg-slate-50">
                    <td className="py-3 px-4">{professor.nome}</td>
                    <td className="py-3 px-4">{professor.email}</td>
                    <td className="py-3 px-4">{professor.formacao}</td>
                    <td className="py-3 px-4">
                      <button
                        onClick={() => removerProfessor(professor.id)}
                        className="bg-red-500 hover:bg-red-600 text-white px-3 py-2 rounded-lg text-sm"
                      >
                        Remover
                      </button>
                    </td>
                  </tr>
                ))}

                {professores.length === 0 && (
                  <tr>
                    <td colSpan="4" className="py-6 text-center text-slate-500">
                      Nenhum professor cadastrado.
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