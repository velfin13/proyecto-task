import { Routes as RoutesEnum } from "@/models";
import { Layout, TaskPage } from "@/pages";
import { Toaster } from "react-hot-toast";
import { Navigate, Route, Routes } from "react-router-dom";

function App() {
  return (
    <>
      <Routes>
        <Route path="/" element={<Navigate to={RoutesEnum.TASK} replace />} />

        <Route path={RoutesEnum.TASK} element={<Layout />}>
          <Route index element={<TaskPage />} />
        </Route>

        {/* Ruta para 404 */}
        <Route path={RoutesEnum.NOT_FOUND} element={<h2>Not found</h2>} />
        <Route
          path={RoutesEnum.ALL}
          element={<Navigate replace to={RoutesEnum.NOT_FOUND} />}
        />
      </Routes>
      <Toaster
        toastOptions={{
          position: "top-right",
        }}
      />

    </>
  );
}

export default App;
