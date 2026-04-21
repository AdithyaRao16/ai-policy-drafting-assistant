from flask import Blueprint, request, jsonify
from prompts.describe_prompt import build_describe_prompt

describe_bp = Blueprint("describe", __name__)

@describe_bp.route("/describe", methods=["POST"])
def describe():
    data = request.get_json()

    if not data or not isinstance(data.get("input"), str) or not data["input"].strip():
        return jsonify({"error": "Invalid or empty input"}), 400

    user_input = data["input"].strip()

    try:
        prompt = build_describe_prompt(user_input)

        return jsonify({
            "status": "success",
            "data": {
                "prompt": prompt
            },
            "message": "Prompt generated successfully"
        }), 200

    except Exception as e:
        return jsonify({
            "status": "error",
            "message": str(e)
        }), 500