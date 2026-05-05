import { useState } from "react";
import Navbar from "./components/Navbar";
import Artigos from "./pages/Artigos";
import Autores from "./pages/Autores";
import Areas from "./pages/Areas";
import Periodicos from "./pages/Periodicos";

function App() {
  const [page, setPage] = useState("artigos");

  return (
    <div>
      <Navbar setPage={setPage} />

      {page === "artigos" && <Artigos />}
      {page === "autores" && <Autores />}
      {page === "areas" && <Areas />}
      {page === "periodicos" && <Periodicos />}
    </div>
  );
}

export default App;