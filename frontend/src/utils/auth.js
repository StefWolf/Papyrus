export function getToken() {
  return localStorage.getItem("token");
}

export function getUserRole() {
  const token = getToken();

  if (!token) return null;

  try {
    const payloadBase64 = token.split(".")[1];
    const payloadJson = atob(payloadBase64);
    const payload = JSON.parse(payloadJson);

    const roles = payload.roles;

    if (!roles) return null;

    if (typeof roles === "string") {
      if (roles.includes("ROLE_ADM")) return "ROLE_ADM";
      if (roles.includes("ROLE_PROFESSOR")) return "ROLE_PROFESSOR";
      if (roles.includes("ROLE_ALUNO")) return "ROLE_ALUNO";
    }

    if (Array.isArray(roles)) {
      const role = roles[0];

      if (typeof role === "string") return role;

      if (role.authority) return role.authority;
    }

    return null;
  } catch (error) {
    return null;
  }
}

export function logout() {
  localStorage.removeItem("token");
}