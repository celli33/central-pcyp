
public class Telefono extends Thread{

	private int id;
	private Central c;
	private int estado;
	static final int cye=0,cyr=1,dyd=2,dym=3,dyl=4,dyo=5,dye=6;
	private int numMarcar;
	
	public Telefono(int id, Central c)
	{
		this.id=id;
		this.c=c;
		estado=cye;
		numMarcar=id;
		
	}
	
	
	public boolean cambiaEstado(int accion)
	{   //acciones realizadas por la persona
		if(accion==Persona.descolgar)
		{  if(estado==cye)
			{ 
			  estado=dyd;
			  System.out.println("telefono "+id+ " : dyd ");
			  return(true);
			}
		   if(estado==cyr)
		   {
			estado=dye;
			System.out.println("telefono "+id+ " : dye ");
			return(true);
		   }
		   return(false);
		}
		else
		{if(accion==Persona.marcar)
		 { 
			if(estado==dyd)
			{
				estado=dym;
				System.out.println("telefono "+id+ " : dym ");
				return(true);
			}
			return(false);
			
		 }
		 //action Persona.colgar
		 else
		 {
			if(estado>=dyd)
			{	estado=cye;
				System.out.println("telefono "+id+ " : cye ");
				return(true);
			}
			return(false);
		 } 
			
		}
	}
	
	public void comunicaCentral()
	{   //emisor
		if(numMarcar!=id)
		{ estado=dyl;
		  c.estaLlamando(numMarcar,id);
		
		}
		//receptor
		if(estado==cye)
		{
			numMarcar=c.llamadaEntrante(id);
			//le están marcando por lo tanto está ocupado
			if(numMarcar!=id)
				c.estableciendoComunicacion();
		}
		c.cambiarEstado(id, estado);
	}
	
	public void setNumMarcar(int num)
	{
		numMarcar=num;
	}
	
	public void run()
	{
		while(true)
		{
			comunicaCentral();
			
		}
	}
}
