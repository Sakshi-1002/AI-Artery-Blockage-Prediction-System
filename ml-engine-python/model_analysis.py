import pandas as pd

from sklearn.model_selection import train_test_split
from sklearn.linear_model import LogisticRegression
from sklearn.ensemble import RandomForestClassifier
from xgboost import XGBClassifier

from sklearn.metrics import accuracy_score, precision_score, recall_score, f1_score

import joblib
 
#Load processed dataset
df = pd.read_csv("../dataset/processed_heart.csv")

#seperate features and target
X = df.drop("target", axis = 1)
y = df["target"]

# Train-test split
X_train, X_test, y_train, y_test = train_test_split(
    X, y,
    test_size=0.2,
    random_state=42
)

# Models dictionary
models = {
    "Logistic Regression": LogisticRegression(),
    "Random Forest": RandomForestClassifier(),
    "XGBoost": XGBClassifier()
}

best_model = None
best_accuracy = 0

print("\nMODEL PERFORMANCE REPORT\n")

# Train and Evaluate each model
for name, model in models.items():

    # Train model
    model.fit(X_train, y_train)

    # Predictions
    y_pred = model.predict(X_test)

    # Metrics
    accuracy = accuracy_score(y_test, y_pred)
    precision = precision_score(y_test, y_pred)
    recall = recall_score(y_test, y_pred)
    f1 = f1_score(y_test, y_pred)

    print(f"Model    : {name}")
    print(f"Accuracy : {accuracy:.4f}")
    print(f"Precision: {precision:.4f}")
    print(f"Recall   : {recall:.4f}")
    print(f"F1-Score : {f1:.4f}")
    print("-" * 40)

    # Select best model
    if accuracy > best_accuracy:
        best_accuracy = accuracy
        best_model = model

# Save best model
joblib.dump(best_model, "../ml-model.pkl")
print("\nBest model saved successfully as ml-model.pkl")
