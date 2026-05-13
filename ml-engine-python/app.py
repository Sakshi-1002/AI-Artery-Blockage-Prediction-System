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
        probabillity = model.predict_proba(features)[0][1]

        # Risk label
        risk_level = "High Risk" if prediction == 0 else "Low Risk"

        #JSON response
        response = {
            "prediction" : int(prediction),
            "risk_level" : risk_level,
            "confidence" : round(float(probabillity), 4),
            "message" : "Prediction generated successfully"
        }

        return jsonify(response)
    
    except Exception as e:
        return jsonify({
            "error": str(e)
        })
    
# Run Flask server
if __name__ == "__main__":
    app.run(debug=True)