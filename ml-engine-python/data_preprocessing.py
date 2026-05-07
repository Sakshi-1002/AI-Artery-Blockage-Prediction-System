import pandas as pd
from sklearn.preprocessing import StandardScaler

# Load dataset
df = pd.read_csv("../dataset/heart.csv")

print("Original Dataset Shape:")
print(df.shape)

# Remove duplicate rows
df = df.drop_duplicates()

print("\nShape After Removing Duplicates:")
print(df.shape)

# Separate features and target
X = df.drop("target", axis=1)
y = df["target"]

# Feature scaling
scaler = StandardScaler()
X_scaled = scaler.fit_transform(X)

# Convert scaled data back to DataFrame
X_scaled_df = pd.DataFrame(X_scaled, columns=X.columns)

# Add target column back
processed_df = X_scaled_df.copy()
processed_df["target"] = y.values

# Save processed dataset
processed_df.to_csv("../dataset/processed_heart.csv", index=False)

print("\nPreprocessing completed successfully.")
print("Processed dataset saved as processed_heart.csv")