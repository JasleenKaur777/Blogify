package com.example.Blogify.payloads;

public class JWTAuthResponse {
	private String token;
    private long expiresIn;
    
    public JWTAuthResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JWTAuthResponse(String token, long expiresIn) {
		super();
		this.token = token;
		this.expiresIn = expiresIn;
	}

	public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
