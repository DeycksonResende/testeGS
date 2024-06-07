import styles from "../app/page.module.css";
import Link from "next/link";
import { motion } from "framer-motion";
import React from "react";

const Home = () => {
  return (
    <main className={styles.main}>
      <nav className={styles.Nav}>
      <img
          className={styles.LogoMarca}
          src="/image/LogoMarca3.svg"
          alt="LogoMarca"
        />
        <Link href="/" legacyBehavior>
          <a className={styles.navSpan}>Home</a>
        </Link>
        <span className={styles.navSpan}>About</span>
        <span className={styles.navSpan}>Services</span>
        <span className={styles.navSpan}>Contact</span>
        <div className={styles.LinksAcess}>
          <Link href="/TelaLogin" legacyBehavior>
            <a className={styles.navSpan}>Login</a>
          </Link>
          <Link href="/TelaCadastro" legacyBehavior>
            <a className={styles.navSpan}>Registrar</a>
          </Link>
        </div>
      </nav>
    </main>
  );
};

export default Home;
