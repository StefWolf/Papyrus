import { useEffect, useState } from "react";
import api from "../services/api";

export default function Artigos() {
  const [artigos, setArtigos] = useState([]);
  const [loading, setLoading] = useState(true);

  const [autores, setAutores] = useState([]);
  const [areas, setAreas] = useState([]);
  const [periodicos, setPeriodicos] = useState([]);

  // 🔥 STATES COMO STRING (IMPORTANTE)
  const [autorSelecionado, setAutorSelecionado] = useState("");
  const [areaId, setAreaId] = useState("");
  const [periodicoId, setPeriodicoId] = useState("");

  const [doi, setDoi] = useState("");
  const [observacoes, setObservacoes] = useState("");
  const [nome, setNome] = useState("");
  const [ano, setAno] = useState("");
  const [status, setStatus] = useState("PARA_LER");

  const [mostrarForm, setMostrarForm] = useState(false);
  const [busca, setBusca] = useState("");

  useEffect(() => {
    carregar();
  }, []);

  async function carregar() {
    try {
      const [a, au, ar, p] = await Promise.all([
        api.get("/artigos"),
        api.get("/autores"),
        api.get("/areas"),
        api.get("/periodicos")
      ]);

      setArtigos(a.data);
      setAutores(au.data);
      setAreas(ar.data);
      setPeriodicos(p.data);
      setLoading(false);
    } catch (e) {
      console.error(e);
    }
  }

  async function criar() {
    console.log("VALORES:", {
  areaId,
  periodicoId,
  autorSelecionado
});

console.log("CONVERTIDOS:", {
  areaId: toNumberOrNull(areaId),
  periodicoId: toNumberOrNull(periodicoId),
  autorSelecionado: toNumberOrNull(autorSelecionado)
});


    if (nome.length < 3) {
      alert("Nome deve ter pelo menos 3 caracteres");
      return;
    }

    if (!areaId) {
      alert("Selecione uma área");
      return;
    }

    if (!periodicoId) {
      alert("Selecione um periódico");
      return;
    }

    if (!autorSelecionado) {
      alert("Selecione um autor");
      return;
    }

    try {
      const payload = {
        nome,
        ano: Number(ano),
        areaId: Number(areaId),
        periodicoId: Number(periodicoId),
        autoresIds: [Number(autorSelecionado)],
        doi,
        observacoes,
        status
      };

      console.log("ENVIANDO:", payload);

      await api.post("/artigos", payload);

      // reset
      setNome("");
      setAno("");
      setStatus("PARA_LER");
      setAutorSelecionado("");
      setAreaId("");
      setPeriodicoId("");
      setDoi("");
      setObservacoes("");

      setMostrarForm(false);
      carregar();

    } catch (e) {
      console.error(e);
      alert("Erro ao criar artigo");
    }
  }

  async function deletar(id) {
    if (!confirm("Deseja deletar?")) return;

    await api.delete(`/artigos/${id}`);
    carregar();
  }

  const filtrados = artigos.filter(a =>
    a.nome.toLowerCase().includes(busca.toLowerCase())
  );

  return (
    <div className="p-6">
      {/* Título */}
      <h1 className="text-3xl font-bold mb-6">📚 Artigos</h1>

      {/* BUSCA + BOTÃO */}
      <div className="flex gap-2 mb-4">
        <input
          placeholder="Buscar artigo..."
          value={busca}
          onChange={e => setBusca(e.target.value)}
          className="border p-2 rounded w-full"
        />

        <button
          onClick={() => setMostrarForm(!mostrarForm)}
          className="bg-blue-500 text-white px-4 rounded hover:bg-blue-600"
        >
          Novo Artigo
        </button>
      </div>

      {/* FORM */}
      {mostrarForm && (
        <div className="bg-white p-4 rounded-xl shadow mb-6 grid grid-cols-3 gap-3">

          <input
            placeholder="Nome"
            value={nome}
            onChange={e => setNome(e.target.value)}
            className="border p-2 rounded"
          />

          <input
            placeholder="Ano"
            value={ano}
            onChange={e => setAno(e.target.value)}
            className="border p-2 rounded"
          />

          <select
            value={status}
            onChange={e => setStatus(e.target.value)}
            className="border p-2 rounded"
          >
            <option value="PARA_LER">Para ler</option>
            <option value="LENDO">Lendo</option>
            <option value="FINALIZADO">Finalizado</option>
          </select>

          {/* AUTOR */}
          <select
            value={autorSelecionado}
            onChange={e => setAutorSelecionado(e.target.id)}
            className="border p-2 rounded"
          >
            <option value="">Selecione um autor</option>
            {autores.map(a => (
              <option key={a.id} value={a.id}>{a.nome}</option>
            ))}
          </select>

          {/* AREA */}
          <select
            value={areaId}
            onChange={e => setAreaId(e.target.id)}
            className="border p-2 rounded"
          >
            <option value="">Selecione uma área</option>
            {areas.map(a => (
              <option key={a.id} value={a.id}>{a.nome}</option>
            ))}
          </select>

          {/* PERIODICO */}
          <select
            value={periodicoId}
            onChange={e => setPeriodicoId(e.target.id)}
            className="border p-2 rounded"
          >
            <option value="">Selecione um periódico</option>
            {periodicos.map(p => (
              <option key={p.id} value={p.id}>{p.nome}</option>
            ))}
          </select>

          <input
            placeholder="DOI"
            value={doi}
            onChange={e => setDoi(e.target.value)}
            className="border p-2 rounded col-span-2"
          />

          <input
            placeholder="Observações"
            value={observacoes}
            onChange={e => setObservacoes(e.target.value)}
            className="border p-2 rounded col-span-3"
          />

          <div className="col-span-3 flex gap-2">
            <button
              onClick={criar}
              className="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600"
            >
              Salvar
            </button>

            <button
              onClick={() => setMostrarForm(false)}
              className="bg-gray-300 px-4 py-2 rounded"
            >
              Cancelar
            </button>
          </div>

        </div>
      )}

      {/* LISTA */}
      {loading ? (
        <p>Carregando...</p>
      ) : filtrados.length === 0 ? (
        <p>Nenhum artigo encontrado</p>
      ) : (
        <div className="grid grid-cols-3 gap-4">
          {filtrados.map((a) => (
            <div
              key={a.id}
              className="bg-white p-4 rounded-xl shadow hover:shadow-lg transition"
            >
              <h2 className="text-lg font-bold">{a.nome}</h2>

              <p className="text-gray-500">{a.ano}</p>

              <span className={`text-sm px-2 py-1 rounded 
                ${a.status === "FINALIZADO" ? "bg-green-200 text-green-800" :
                  a.status === "LENDO" ? "bg-yellow-200 text-yellow-800" :
                  "bg-gray-200 text-gray-800"}
              `}>
                {a.status}
              </span>

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