import os
import time
import requests

GROQ_API_KEY = os.getenv("GROQ_API_KEY")
GROQ_URL = "https://api.groq.com/openai/v1/chat/completions"
MODEL = "llama-3.3-70b-versatile"


def call_groq(prompt: str, retries: int = 3):
    headers = {
        "Authorization": f"Bearer {GROQ_API_KEY}",
        "Content-Type": "application/json"
    }

    payload = {
        "model": MODEL,
        "messages": [
            {"role": "user", "content": prompt}
        ],
        "temperature": 0.3,
        "max_tokens": 2000
    }

    for attempt in range(retries):
        try:
            response = requests.post(GROQ_URL, json=payload, headers=headers)

            if response.status_code == 200:
                return response.json()["choices"][0]["message"]["content"]

        except Exception as e:
            print(f"[Groq Error] Attempt {attempt+1}: {e}")

        time.sleep(2 ** attempt)

    # fallback (MANDATORY per spec)
    return "AI service temporarily unavailable. Please try again later."