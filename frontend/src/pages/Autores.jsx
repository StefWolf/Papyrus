import { useEffect, useState } from "react";
import api from "../services/api";

export default function Autores() {
  const [autores, setAutores] = useState([]);
  const [loading, setLoading] = useState(true);

  // form
  const [nome, setNome] = useState("");
  const [email, setEmail] = useState("");
  const [lattes, setLattes] = useState("");

  // busca
  const [busca, setBusca] = useState("");

  useEffect(() => {
    carregar();
  }, []);

  async function carregar() {
    try {
      setLoading(true);
      const res = await api.get("/autores");
      setAutores(res.data);
    } catch (e) {
      //alert("Erro ao carregar autores");
    } finally {
      setLoading(false);
    }
  }

  async function criar() {
    if (!nome || !email) {
     // alert("Nome e email são obrigatórios");
      return;
    }

    try {
      await api.post("/autores", {
        nome,
        email,
        lattes
      });

      setNome("");
      setEmail("");
      setLattes("");

      carregar();
    } catch (e) {
      //alert("Erro ao criar autor");
    }
  }

  async function deletar(id) {
    if (!confirm("Deseja deletar este autor?")) return;

    try {
      await api.delete(`/autores/${id}`);
      carregar();
    } catch (e) {
      //alert("Erro ao deletar (pode ter artigos vinculados)");
    }
  }

  const filtrados = autores.filter(a =>
    a.nome.toLowerCase().includes(busca.toLowerCase())
  );

  return (
    <div className="p-6">
      {/* Título */}
      <h1 className="text-3xl font-bold mb-6">👤 Autores</h1>

      {/* FORM */}
      <div className="bg-white p-4 rounded-xl shadow mb-6 grid grid-cols-4 gap-3">
        <input
          placeholder="Nome"
          value={nome}
          onChange={e => setNome(e.target.value)}
          className="border p-2 rounded"
        />

        <input
          placeholder="Email"
          value={email}
          onChange={e => setEmail(e.target.value)}
          className="border p-2 rounded"
        />

        <input
          placeholder="Lattes"
          value={lattes}
          onChange={e => setLattes(e.target.value)}
          className="border p-2 rounded"
        />

        <button
          onClick={criar}
          className="bg-blue-500 text-white rounded px-4 py-2 hover:bg-blue-600"
        >
          Criar
        </button>
      </div>

      {/* BUSCA */}
      <input
        placeholder="Buscar autor..."
        value={busca}
        onChange={e => setBusca(e.target.value)}
        className="border p-2 rounded w-full mb-4"
      />

      {/* LISTA */}
      {loading ? (
        <p>Carregando...</p>
      ) : filtrados.length === 0 ? (
        <p>Nenhum autor encontrado</p>
      ) : (
        <div className="grid grid-cols-3 gap-4">
          {filtrados.map((a, index) => (
            <div
              key={index}
              className="bg-white p-4 rounded-xl shadow hover:shadow-lg transition"
            >
              <h2 className="text-lg font-bold">{a.nome}</h2>

              <p className="text-gray-500 text-sm">{a.email}</p>

              {a.lattes && (
                <a
                  href={a.lattes}
                  target="_blank"
                  className="text-blue-500 text-sm underline"
                >
                  Lattes
                </a>
              )}

              <p className="text-sm mt-2">
                📄 Artigos: {a.qtdArtigosRegistrados}
              </p>

              {/* BOTÃO */}
              <div className="mt-3">
                <button
                  onClick={() => deletar(a.id)}
                  className="text-red-500 hover:underline"
                >
                  Deletar
                </button>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}