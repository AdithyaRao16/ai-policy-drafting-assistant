from flask import Flask
from flask_cors import CORS

def create_app():
    app = Flask(__name__)
    CORS(app)

    from routes.describe import describe_bp
    from routes.recommend import recommend_bp

    app.register_blueprint(describe_bp, url_prefix="/api")
    app.register_blueprint(recommend_bp, url_prefix="/api")

    @app.route("/health")
    def health():
        return {"status": "ok"}, 200

    return app

if __name__ == "__main__":
    app = create_app()
    app.run(host="0.0.0.0", port=5000, debug=True)