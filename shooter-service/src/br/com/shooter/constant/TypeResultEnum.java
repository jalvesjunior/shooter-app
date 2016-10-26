package br.com.shooter.constant;

public enum TypeResultEnum {

	WIN("Ganhador"), LOS("Perdedor"), TIE("Empate");
	
	private String description;

	TypeResultEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
