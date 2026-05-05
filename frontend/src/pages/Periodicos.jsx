import { useEffect, useState } from "react";
import api from "../services/api";

export default function Periodicos() {
  const [periodicos, setPeriodicos] = useState([]);
  const [loading, setLoading] = useState(true);

  // form
  const [nome, setNome] = useState("");
  const [link, setLink] = useState("");

  // busca
  const [busca, setBusca] = useState("");

  useEffect(() => {
    carregar();
  }, []);

  async function carregar() {
    try {
      setLoading(true);
      const res = await api.get("/periodicos");
      setPeriodicos(res.data);
    } catch (e) {
      //alert("Erro ao carregar periódicos");
    } finally {
      setLoading(false);
    }
  }

  async function criar() {
    if (!nome) {
      //alert("Nome é obrigatório");
      return;
    }

    try {
      await api.post("/periodicos", {
        nome,
        link
      });

      setNome("");
      setLink("");

      carregar();
    } catch (e) {
      //alert("Erro ao criar periódico");
    }
  }

  async function deletar(id) {
    if (!confirm("Deseja deletar este periódico?")) return;

    try {
      await api.delete(`/periodicos/${id}`);
      carregar();
    } catch (e) {
      //alert("Erro ao deletar (pode ter artigos vinculados)");
    }
  }

  const filtrados = periodicos.filter(p =>
    p.nome.toLowerCase().includes(busca.toLowerCase())
  );

  return (
    <div className="p-6">
      {/* Título */}
      <h1 className="text-3xl font-bold mb-6">📚 Periódicos</h1>

      {/* FORM */}
      <div className="bg-white p-4 rounded-xl shadow mb-6 grid grid-cols-3 gap-3">
        <input
          placeholder="Nome"
          value={nome}
          onChange={e => setNome(e.target.value)}
          className="border p-2 rounded"
        />

        <input
          placeholder="Link"
          value={link}
          onChange={e => setLink(e.target.value)}
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
        placeholder="Buscar periódico..."
        value={busca}
        onChange={e => setBusca(e.target.value)}
        className="border p-2 rounded w-full mb-4"
      />

      {/* LISTA */}
      {loading ? (
        <p>Carregando...</p>
      ) : filtrados.length === 0 ? (
        <p>Nenhum periódico encontrado</p>
      ) : (
        <div className="grid grid-cols-3 gap-4">
          {filtrados.map((p) => (
            <div
              key={p.id}
              className="bg-white p-4 rounded-xl shadow hover:shadow-lg transition"
            >
              <h2 className="text-lg font-bold">{p.nome}</h2>

              {p.link && (
                <a
                  href={p.link}
                  target="_blank"
                  className="text-blue-500 text-sm underline"
                >
                  Acessar periódico
                </a>
              )}

              <p className="text-sm mt-2">
                📄 Artigos: {p.qtdArtigosRegistrados}
              </p>

              {/* BOTÃO */}
              <div className="mt-3">
                <button
                  onClick={() => deletar(p.id)}
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