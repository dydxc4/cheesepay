package com.axolutions.panel;

import java.sql.SQLException;

import com.axolutions.AppContext;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;

public class LoginPanel extends BasePanel
{
    /**
     * Panel de inicio de sesión
     * 
     * Este es el primer panel que ve el usuario al iniciar el programa.
     * 
     * Aquí no se hacen consultas en la BD, simplimente se llama a la función 
     * login() en appContext para intentar iniciar sesión.
     * 
     * Todo ello deberá estar dentro de un bucle que se ejecute mientras no se
     * haya iniciado sesión, o bien, hasta que ocurra un error de conexión,en 
     * cuyo caso el programa debe finalizar.
     * 
     * La clase Console dispone de un metodo para que el usuario ingrese una
     * contraseña sin mostrarla en pantalla.
     */

    /**
     * Crea un nuevo objeto LoginPanel.
     * @param appContext Instancia del objeto AppContext
     */
    public LoginPanel(AppContext appContext)
    {
        super(appContext, Location.LoginPanel);
    }

    public PanelTransitionArgs show(PanelTransitionArgs args)
    {
        showLogo();
        String user, password;
        
        do {
            System.out.println();
            
            user = console.readString("Ingrese su usuario");
            password = console.readPassword("Contraseña");
            
            // Intenta realizar la conexión con las credenciales dadas
            try 
            {
                System.out.print("Conectando... ");
                appContext.login(user, password);
            }
            // Captura un error de conexión
            catch (CommunicationsException e)
            {
                System.out.println("error de conexión");
                
                // Terminar programa
                return setLocation(Location.Exit);
            }
            // Captura un error en las credenciales de acceso
            catch (SQLException e)
            {
                System.out.println("acceso denegado");
                
                // Volver a pedir las credenciales
            }
        // Repite mientras no este conectado a la base de datos
        } while (!appContext.isConnected());
        
        System.out.println("ok");
        
        // Retorna al menú principal
        return setLocation(Location.MainMenu);
    }

    private void showLogo()
    {
        String logo =
            "                                                                  \n" +
            "                        ******************.                       \n" + 
            "                     ,***********************                     \n" + 
            "                    , .*********. .********* *                    \n" + 
            "              ///////*  ******  * ********* ,///////              \n" + 
            "            /////,..*((. *******,   ****** ((/,../////            \n" + 
            "          ////...(((((((, ****  * * ,**** ((((((((..////.         \n" + 
            "      //////.......((((((* ****** ****** ((((((*......,/////      \n" + 
            "     /////..(*......./(/... *********** ...((........(/..////     \n" + 
            "     ///..((((((............ ********, ...........*(((((..///.    \n" + 
            "    ///..(((((((((,........(/ ******, ((........(((((((((*.///,   \n" + 
            "  ////..((((((((,............( ****. *............((((((((*.////  \n" + 
            " ////*....(((/.......,(*....... **. .......((........(((,....//// \n" + 
            " .///..............(((((((......   ...../((((((,.............//// \n" + 
            "  ///..,........,(((((((((((*.... ....((((((((((((........./.*//. \n" + 
            "  ///............,(((((((((,...........((((((((((............///, \n" + 
            " *///......,,.......(((((........*.......*((((,......./......//// \n" + 
            " /////...(((((/.......,......./(((((................(((((,..,//// \n" + 
            "   ////./((((((((,..........((((((((((*........../((((((((..////  \n" + 
            "    ////./(((((((,..........(((((((((((..........((((((((..///    \n" + 
            "     ////..((((.............../(((((,..............*(((/.*///.    \n" + 
            "      /////........./((((........(........((((.......,../////     \n" + 
            "       */////.....(((((((((,.........../((((((((*....,/////,      \n" + 
            "           ////,..(((((((((((.........((((((((((..,////*          \n" + 
            "             //////...*(((,............./((/...*//////            \n" + 
            "               /////////,,..............,,//////////              \n" + 
            "                      //////////////////////.                     \n" + 
            "                          */*.      .*//,                         \n";

        System.out.println(logo);
        System.out.println("Bienvenido a CheesePay v1.0");
    }
}
