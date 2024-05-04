package me.scholagate.api.utils;

import com.password4j.Hash;
import com.password4j.Password;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author JonathanBetPer
 * @version v2
 * @since 20/02/2023
 *
 * Clase Encriptacion
 * Utiliza la dependencia password4j (y a su vez la interfaz SLF4J) para cifrar y descifrar contraseñas, con pimienta
 */
public class Encriptacion {
    @Value("${SG_HASH_PEPPER}")
    private static String pimienta;

    /**
     * Genera el hash de una contraseña con pimienta y retorna su resultad
     */
    public static Hash generarHash(String passwd){
        return Password.hash(passwd).addRandomSalt(12).addPepper(pimienta).withScrypt();
    }

    /**
     * Coomprueba que el resultado de un hash con pimienta coincida con una contraseña
     */
    public static boolean comprobarPasswd( String passwd, String hashResult, byte[] salt){
        return Password.check(passwd, hashResult).addSalt(salt).addPepper(pimienta).withScrypt();
    }
}