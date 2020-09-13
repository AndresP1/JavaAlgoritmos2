package GrafoMapa;

public class Punto {
	private Double coordX;
	private Double coordY;

	public Punto(Double x,Double y) {
		coordX=x;
		coordY=y;
	}

	public Double getCoordX() {
		return coordX;
	}

	public void setCoordX(Double coordX) {
		this.coordX = coordX;
	}

	public Double getCoordY() {
		return coordY;
	}

	public void setCoordY(Double coordY) {
		this.coordY = coordY;
	}

	@Override
	public String toString() {
		return   coordX + ";" + coordY ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coordX == null) ? 0 : coordX.hashCode());
		result = prime * result + ((coordY == null) ? 0 : coordY.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		/*if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Punto other = (Punto) obj;
		if (coordX == null) {
			if (other.coordX != null)
				return false;
		} else if (!coordX.equals(other.coordX))
			return false;
		if (coordY == null) {
			if (other.coordY != null)
				return false;
		} else if (!coordY.equals(other.coordY))
			return false;
		return true;*/
		Punto p = (Punto)obj;
		try {
			Double x,y;
			x=Double.parseDouble(p.getCoordX().toString());
			y=Double.parseDouble(p.getCoordY().toString());
			
			return coordX.equals(x) && coordY.equals(y);
		}
		catch(Exception ex) {
			return false;
		}
		
	}
	
	
}
