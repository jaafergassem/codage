/**
 * 
 */
package clientServer;

import java.net.InetAddress;
import java.net.Socket;
import java.util.Vector;

/**
 * @author Mickael
 * 
 */
public class Server extends Thread
{
	// Liste des clients que connait le serveur
	private Vector<ClientServerData> clients;
	// Thread d'ecoute du serveur
	private ThreadListenerTCP threadListener;

	/**
	 * Construct Server() Constructeur le la class Server. Initialise les
	 * variables server,clients et threadListener. En ouvrant sur le port 30972
	 */
	public Server()
	{
		this.clients = new Vector<ClientServerData>();
		this.threadListener = new ThreadListenerTCP(this, 30972);
	}

	/**
	 * Construct Server(int port) Constructeur de la class Server. Initialise
	 * les variables server,clients et threadListener.
	 * 
	 * @param int numero de port
	 */
	public Server(int port)
	{
		this.clients = new Vector<ClientServerData>();
		this.threadListener = new ThreadListenerTCP(this, port);
	}

	/**
	 * Method launch() Méthode permettant de lancer le serveur.
	 */
	public void launch()
	{
		this.threadListener.start();
	}

	/**
	 * Method stopServer() Méthode permettant de stopper le serveur.
	 */
	public void stopServer()
	{
		this.threadListener.stopThread();
	}

	/**
	 * Method addClient
	 * 
	 * @param name
	 *            Nom du client
	 * @param client
	 *            Socket du client
	 * @return true si client ajouté, false sinon.
	 */
	public boolean addClient(String name, Socket client)
	{
		return this.clients.add(new ClientServerData(name, client.getLocalAddress(), client.getLocalPort()));
	}

	/**
	 * Method removeClient
	 * 
	 * @param client
	 *            {@link ClientServerData}
	 * @return true si réussit, false sinon
	 */
	public boolean removeClient(ClientServerData client)
	{
		return this.clients.remove(client);
	}

	/**
	 * Method removeClient
	 * 
	 * @param name
	 *            Nom du client
	 * @return true si réussit, false sinon
	 */
	public boolean removeClient(String name)
	{
		boolean erase = false;
		for (ClientServerData client : this.clients)
		{
			if (client.getName().equals(name))
			{
				this.clients.remove(client);
				erase = true;
			}
		}
		return erase;
	}

	/**
	 * Method removeClient
	 * 
	 * @param ip
	 *            Ip du client {@link InetAddress}
	 * @return true si reussit, false sinon
	 */
	public boolean removeClient(InetAddress ip)
	{
		boolean erase = false;
		for (ClientServerData client : this.clients)
		{
			if (client.getIp().equals(ip))
			{
				this.clients.remove(client);
				erase = true;
			}
		}
		return erase;
	}

	public Vector<ClientServerData> getClients()
	{
		return clients;
	}

	public void setClients(Vector<ClientServerData> clients)
	{
		this.clients = clients;
	}

	public ThreadListenerTCP getThreadListener()
	{
		return threadListener;
	}

	public void setThreadListener(ThreadListenerTCP threadListener)
	{
		this.threadListener = threadListener;
	}

}