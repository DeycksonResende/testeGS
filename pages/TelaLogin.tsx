"use client";
import React, { useState } from "react";
import { motion } from "framer-motion";
import Link from "next/link";
import styles from "./TelaLogin.module.css";
import Nav from "@/Componentes/NavLogin";

const LoginPage = () => {
  const containerVariants = {
    hidden: { opacity: 0 },
    visible: {
      opacity: 1,
      transition: {
        delay: 0.5,
        duration: 2,
        when: "beforeChildren",
        staggerChildren: 0.3,
      },
    },
  };

  const itemVariants = {
    hidden: { opacity: 0, y: 50 },
    visible: {
      opacity: 1,
      y: 0,
      transition: {
        type: "spring",
        stiffness: 50,
        duration: 2,
      },
    },
  };

  const backdropVariants = {
    hidden: { opacity: 0 },
    visible: {
      opacity: 1,
      transition: {
        duration: 2,
      },
    },
  };

  return (
    <motion.div
      className={styles.backdrop}
      initial="hidden"
      animate="visible"
      variants={backdropVariants}
    >
      <motion.main
        className={styles.TelaLogin}
        variants={containerVariants}
        initial="hidden"
        animate="visible"
      >
        <Nav />
        <motion.form className={styles.Form} variants={itemVariants}>
          <motion.h2 className={styles.title} variants={itemVariants}>
            Login
          </motion.h2>
          <div className={styles.Restante}>
            <motion.div className={styles.inputGroup} variants={itemVariants}>
              <input
                type="text"
                required
                className={styles.input}
                id="userName"
              />
              <label htmlFor="userName">Nome de usuario</label>
              <img
                className={styles.icon}
                src="/image/iconUser.png"
                alt="Logo"
              />
            </motion.div>
            <motion.div className={styles.inputGroup} variants={itemVariants}>
              <input
                type="password"
                required
                className={styles.input}
                id="cityName"
              />
              <label htmlFor="cityName">Senha</label>
              <img
                className={styles.icon}
                src="/image/iconPassword.png"
                alt="Logo"
              />
            </motion.div>
            <motion.div className={styles.remember} variants={itemVariants}>
              <label className={styles.label}>
                <input type="checkbox" />
                <span className={styles.checkbox}></span>
                <div className={styles.Option}>
                  <span className={styles.label}>Lembrar-me</span>
                  <span className={styles.label2}>Esqueceu a senha?</span>
                </div>
              </label>
            </motion.div>
            <motion.button
              type="submit"
              className={styles.button}
              whileHover={{ scale: 1.02 }}
              transition={{ type: "spring", stiffness: 300 }}
            >
              Entrar
            </motion.button>
            <motion.p className={styles.Account} variants={itemVariants}>
              Não tem uma conta?
              <Link href="/TelaCadastro" legacyBehavior>
                <a className={styles.Register}>Registre-se</a>
              </Link>
            </motion.p>
          </div>
        </motion.form>
      </motion.main>
    </motion.div>
  );
};

export default LoginPage;
