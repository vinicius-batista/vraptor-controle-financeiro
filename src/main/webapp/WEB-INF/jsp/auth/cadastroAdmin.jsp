<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:logged-page>
  <jsp:body>
    <div class="flex w-full justify-center mt-5">
      <div class="bg-white shadow w-3/5 p-4">
        <h4 class="text-grey-darker text-center text-xl">
          Cadastrar Usuário
        </h4>
        <t:error-box />
        <form
          action="/controle-financeiro/auth/cadastrar-admin"
          class="px-8 pt-6 pb-8"
        >
          <div class="mb-4 flex">
            <label
              class="block w-1/5 text-grey-darker text-sm text-center pt-3 pr-5 font-bold"
              for="usuario.nome"
            >
              Nome
            </label>
            <input
              class="w-4/5 bg-grey-lighter appearance-none border-2 border-grey-lighter rounded shadow w-full py-2 px-4 mr-5 text-grey-darker leading-tight focus:outline-none focus:bg-white focus:border-blue-light"
              id="usuario.nome"
              name="usuario.nome"
              placeholder="Josenaldo da Costa"
            />
          </div>
          <div class="mb-4 flex">
            <label
              class="block w-1/5 text-grey-darker text-sm text-center pt-3 pr-5 font-bold"
              for="usuario.email"
            >
              Email
            </label>
            <input
              class="w-4/5 bg-grey-lighter appearance-none border-2 border-grey-lighter rounded shadow w-full py-2 px-4 mr-5 text-grey-darker leading-tight focus:outline-none focus:bg-white focus:border-blue-light"
              id="usuario.email"
              name="usuario.email"
              placeholder="email@email.com"
            />
          </div>
          <div class="mb-4 flex">
            <label
              class="block w-1/5 text-grey-darker text-sm text-center pt-3 pr-5 font-bold"
              for="usuario.senha"
            >
              Senha
            </label>
            <input
              class="w-4/5 bg-grey-lighter appearance-none border-2 border-grey-lighter rounded shadow w-full py-2 px-4 mr-5 text-grey-darker leading-tight focus:outline-none focus:bg-white focus:border-blue-light"
              placeholder="******************"
              type="password"
              id="usuario.senha"
              name="usuario.senha"
            />
          </div>
          <div class="mb-4 flex">
            <label
              class="block w-1/5 text-grey-darker text-sm text-center pt-3 pr-5 font-bold"
              for="usuario.role"
            >
              Role
            </label>
            <div class="relative w-4/5 mr-5">
              <select
                class="block appearance-none shadow w-full bg-grey-lighter px-4 py-2 pr-4 rounded leading-tight focus:outline-none focus:shadow-outline"
                name="usuario.role"
                id="usuario.role"
              >
                <option value="user">user</option>
                <option value="admin">admin</option>
              </select>
              <div
                class="pointer-events-none absolute pin-y pin-r flex items-center px-2 text-grey-darker"
              >
                <i class="fas fa-arrow-down"></i>
              </div>
            </div>
          </div>
          <div class="flex justify-center">
            <button
              class="w-1/2 bg-blue hover:bg-blue-dark text-white font-bold py-3 mt-3 px-4 rounded focus:outline-none focus:shadow-outline"
              type="submit"
            >
              CADASTRAR
            </button>
          </div>
        </form>
      </div>
    </div>
  </jsp:body>
</t:logged-page>