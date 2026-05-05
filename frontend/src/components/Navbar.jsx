export default function Navbar({ setPage }) {
  return (
    <div className="bg-gray-900 text-white p-4 flex gap-4">
      <button onClick={() => setPage("artigos")}>Artigos</button>
      <button onClick={() => setPage("autores")}>Autores</button>
      <button onClick={() => setPage("areas")}>Áreas</button>
      <button onClick={() => setPage("periodicos")}>Periódicos</button>
    </div>
  );
}