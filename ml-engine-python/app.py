from flask import Flask, request, jsonify
import numpy as np
import joblib

# Initialize Flask app
app = Flask(__name__)

# Load trained model
model = joblib.load("../ml-model.pkl")

# Home route
@app.route("/")
def home():
    return "AI Artery Blockage Prediction API Running"

# Prediction route
@app.route("/predict", methods=["POST"])
def predict():

    try:
        #Get JSON request data
        data = request.get_json()

        # Convert input into numpy array
        features = np.array([
            data["age"],
            data["sex"],
            data["cp"],
            data["trestbps"],
            data["chol"],
            data["fbs"],
            data["restecg"],
            data["thalach"],
            data["exang"],
            data["oldpeak"],
            data["slope"],
            data["ca"],
            data["thal"]
        ]).reshape(1, -1)

        # Predict using model
        prediction = model.predict(features)[0]

        # Prediction probabillity
        probabillity = model.predict_proba(features)[0][0]

        print("Prediction:", prediction)
        print("Probability:", probabillity)

        # Risk label
        if probabillity >= 0.60:
            risk_level = "High Risk"

        elif probabillity >= 0.35:
            risk_level = "Moderate Risk"

        else:
            risk_level = "Low Risk"

        #JSON response
        response = {
            "prediction": int(prediction),
            "risk_level": risk_level,
            "confidence": round(float(probabillity), 4),
            "message": f"Patient classified as {risk_level}"
        }

        return jsonify(response)
    
    except Exception as e:
        return jsonify({
            "error": str(e)
        })
    
# Run Flask server
if __name__ == "__main__":
    app.run(debug=True)

