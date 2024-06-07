"use client";
import React from "react";
import { motion } from "framer-motion";
import Link from "next/link";
import styles from "./TelaCadastro.module.css";
import Nav from "@/Componentes/NavCadastro";

const CadastroPage = () => {
  const pageVariants = {
    hidden: {
      opacity: 0,
      x: "100vw",
      scale: 0.8,
      rotate: 360,
    },
    visible: {
      opacity: 1,
      x: 0,
      scale: 1,
      rotate: 0,
      transition: {
        type: "spring",
        duration: 4,
        bounce: 0.3,
        when: "beforeChildren",
        staggerChildren: 0.4,
      },
    },
  };

  const itemVariants = {
    hidden: { opacity: 0, y: 50 },
    visible: { opacity: 1, y: 0 },
  };

  return (
    <motion.div
      className={styles.backdrop}
      initial="hidden"
      animate="visible"
      variants={pageVariants}
    >
      <nav className={styles.Nav}>
        <Nav />
      </nav>
      <main className={styles.TelaCadastro}>
        <motion.div className={styles.Center} variants={itemVariants}>
          <div className={styles.Left}>
            <video
              className={styles.Capa}
              src="/video/CadastroFundo1.webm"
              autoPlay
              loop
              muted
            ></video>
            <div className={styles.ConteudoLeft}>
              <img
                className={styles.LogoMarca}
                src="/image/LogoMarca2.svg"
                alt="LogoMarca"
              />
              <h1 className={styles.Titulo}>
                <span className={styles.Span2}>Bem-vindo</span> à Blue{" "}
                <span className={styles.Span}>Vision</span>
              </h1>
              <h3 className={styles.Subtitulo}>
                Junte-se a nós na preservação dos nossos oceanos para um futuro
                sustentável
              </h3>
              <p className={styles.Texto}>
                Na Blue Vision, acreditamos no poder da comunidade para
                impulsionar mudanças positivas. Ao se juntar à nossa plataforma,
                você fará parte de um esforço global para proteger e preservar
                os habitats marinhos. Conheça nossas iniciativas, participe de
                campanhas e conecte-se com indivíduos e organizações dedicados a
                fazer a diferença.
              </p>
              <div className={styles.IconsContainer}>
                <img
                  className={styles.Logos}
                  src="/image/Instagram.svg"
                  alt="Logo"
                />
                <img
                  className={styles.Logos}
                  src="/image/twitter.svg"
                  alt="Logo"
                />
                <img
                  className={styles.Logos}
                  src="/image/facebook.svg"
                  alt="Logo"
                />
                <img
                  className={styles.Logos}
                  src="/image/linkedin.svg"
                  alt="Logo"
                />
              </div>
            </div>
          </div>
          <motion.div className={styles.Right} variants={itemVariants}>
            <form className={styles.Form}>
              <h2 className={styles.title}>Crie sua conta</h2>
              <div className={styles.Restante}>
                <div className={styles.inputGroup}>
                  <input
                    type="text"
                    required
                    className={styles.input}
                    id="userName"
                  />
                  <label htmlFor="userName">Nome</label>
                  <img
                    className={styles.icon}
                    src="/image/IconNome.png"
                    alt="Ícone de usuário"
                  />
                </div>
                <div className={styles.inputGroup}>
                  <input
                    type="email"
                    required
                    className={styles.input}
                    id="userEmail"
                  />
                  <label htmlFor="userEmail">Email</label>
                  <img
                    className={styles.icon}
                    src="/image/EmailLogo.png"
                    alt="Ícone de email"
                  />
                </div>
                <div className={styles.inputGroup}>
                  <input
                    type="password"
                    required
                    className={styles.input}
                    id="userPassword"
                  />
                  <label htmlFor="userPassword">Senha</label>
                  <img
                    className={styles.icon}
                    src="/image/SenhaLogo.png"
                    alt="Ícone de senha"
                  />
                </div>
                <div className={styles.inputGroup}>
                  <input
                    type="text"
                    required
                    className={styles.input}
                    id="userCep"
                  />
                  <label htmlFor="userCep">Cep</label>
                  <img
                    className={styles.icon}
                    src="/image/Cep.png"
                    alt="Ícone de CEP"
                  />
                </div>
                <div className={styles.remember}>
                  <label className={styles.label}>
                    <input type="checkbox" />
                    <span className={styles.checkbox}></span>
                    <div className={styles.Option}>
                      <span className={styles.label}>
                        Aceitar os termos & condições
                      </span>
                    </div>
                  </label>
                </div>
                <motion.button
                  type="submit"
                  className={styles.button}
                  whileHover={{ scale: 1.02 }}
                  transition={{ type: "spring", stiffness: 300 }}
                >
                  Cadastre-se
                </motion.button>
                <p className={styles.Account}>
                  Já tem uma conta registrada?{" "}
                  <Link href="/TelaCadastro" legacyBehavior>
                    <a className={styles.Register}>Logar</a>
                  </Link>
                </p>
              </div>
            </form>
          </motion.div>
        </motion.div>
      </main>
    </motion.div>
  );
};

export default CadastroPage;
