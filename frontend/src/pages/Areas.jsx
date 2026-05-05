import { useEffect, useState } from "react";
import api from "../services/api";

export default function Areas() {
  const [areas, setAreas] = useState([]);
  const [loading, setLoading] = useState(true);

  // form
  const [nome, setNome] = useState("");

  // busca
  const [busca, setBusca] = useState("");

  useEffect(() => {
    carregar();
  }, []);

  async function carregar() {
    try {
      setLoading(true);
      const res = await api.get("/areas");
      setAreas(res.data);
    } catch (e) {
      //alert("Erro ao carregar áreas");
    } finally {
      setLoading(false);
    }
  }

  async function criar() {
    if (!nome) {
     // alert("Nome é obrigatório");
      return;
    }

    try {
      await api.post("/areas", { nome });

      setNome("");
      carregar();
    } catch (e) {
      //alert("Erro ao criar área");
    }
  }

  async function deletar(id) {
    if (!confirm("Deseja deletar esta área?")) return;

    try {
      await api.delete(`/areas/${id}`);
      carregar();
    } catch (e) {
      //alert("Erro ao deletar (pode ter artigos vinculados)");
    }
  }

  const filtradas = areas.filter(a =>
    a.nome.toLowerCase().includes(busca.toLowerCase())
  );

  return (
    <div className="p-6">
      {/* Título */}
      <h1 className="text-3xl font-bold mb-6">🧪 Áreas</h1>

      {/* FORM */}
      <div className="bg-white p-4 rounded-xl shadow mb-6 grid grid-cols-2 gap-3">
        <input
          placeholder="Nome da área"
          value={nome}
          onChange={e => setNome(e.target.value)}
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
        placeholder="Buscar área..."
        value={busca}
        onChange={e => setBusca(e.target.value)}
        className="border p-2 rounded w-full mb-4"
      />

      {/* LISTA */}
      {loading ? (
        <p>Carregando...</p>
      ) : filtradas.length === 0 ? (
        <p>Nenhuma área encontrada</p>
      ) : (
        <div className="grid grid-cols-3 gap-4">
          {filtradas.map((a) => (
            <div
              key={a.id}
              className="bg-white p-4 rounded-xl shadow hover:shadow-lg transition flex flex-col justify-between"
            >
              <div>
                <h2 className="text-lg font-bold">{a.nome}</h2>

                <p className="text-sm mt-2 text-gray-600">
                  📄 Artigos: {a.qtdArtigosRegistrados}
                </p>
              </div>

              {/* BOTÃO */}
              <button
                onClick={() => deletar(a.id)}
                className="text-red-500 hover:underline mt-3 self-start"
              >
                Deletar
              </button>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}