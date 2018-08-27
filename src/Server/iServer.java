package Server;

public interface iServer {
	void start(iClientHandler ch);// start a communication
	void stop();
	
}
