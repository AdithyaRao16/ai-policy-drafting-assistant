from flask import Blueprint, request, jsonify

describe_bp = Blueprint("describe", __name__)

@describe_bp.route("/describe", methods=["POST"])
def describe():
    data = request.get_json()

    if not data or "input" not in data:
        return jsonify({"error": "Invalid input"}), 400

    return jsonify({"message": "Describe working"})