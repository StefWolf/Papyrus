import { useNavigate } from "react-router-dom";

export default function Navbar({ setPage }) {
  const navigate = useNavigate();
  return (
    <div className="bg-gray-900 text-white p-4 flex gap-4">
      <button onClick={() => setPage("artigos")}>Artigos</button>
      <button onClick={() => setPage("autores")}>Autores</button>
      <button onClick={() => setPage("areas")}>Áreas</button>
      <button onClick={() => setPage("periodicos")}>Periódicos</button>
      <button
        onClick={handleLogout}
        className="bg-red-500 text-white px-4 py-2 rounded"
      >
        Sair
      </button>
    </div>
  );

  function handleLogout() {
  localStorage.removeItem("token");
  navigate("/login");
}
}