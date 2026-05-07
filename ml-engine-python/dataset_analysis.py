import pandas as pd

# Load dataset
df = pd.read_csv("../dataset/heart.csv")

# Display first 5 rows
print(df.head())

# Dataset shape
print("\nDataset Shape:")
print(df.shape)

# Column names
print("\nColumns:")
print(df.columns.tolist())

# Null values
print("\nNull Values:")
print(df.isnull().sum())

# Data types
print("\nData Types:")
print(df.dtypes)

# Correlation matrix for numeric columns
numeric_df = df.select_dtypes(include=['int64', 'float64'])

print("\nCorrelation Matrix:")
print(numeric_df.corr())