import React from "react";
import styles from "./NavCadastro.module.css";
import { motion } from "framer-motion";
import Link from "next/link";

const Nav = () => {
  return (
    <motion.nav
      className={styles.nav}
      initial={{ x: "150vw", opacity: 0 }} // Começa fora da tela à direita
      animate={{ x: 0, opacity: 1 }} // Anima para a posição inicial e opacidade completa
      transition={{ type: "spring", duration: 4, bounce: 0.25 }} // Tipo de transição e duração
    >
      <Link href="/" legacyBehavior>
        <span className={styles.navSpan}>Home</span>
      </Link>
      <span className={styles.navSpan}>About</span>
      <span className={styles.navSpan}>Services</span>
      <span className={styles.navSpan}>Contact</span>
      <div className={styles.LinksAcess}>
        <Link href="/TelaLogin" legacyBehavior>
          <a className={styles.navLogin}>Login</a>
        </Link>
        <Link href="/TelaCadastro" legacyBehavior>
          <a className={styles.navRegister} style={{ borderBottom: "none" }}>
            Registrar
          </a>
        </Link>
      </div>
    </motion.nav>
  );
};

export default Nav;
