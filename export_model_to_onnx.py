#!/usr/bin/env python3
"""
Export sentence-transformers MiniLM-L6-v2 model to ONNX format.

This script downloads the MiniLM-L6-v2 model from sentence-transformers
and exports it to ONNX format for use with Java/Kotlin applications.

Requirements:
    pip install sentence-transformers optimum onnx onnxruntime

Usage:
    python export_model_to_onnx.py
"""

import os
from pathlib import Path
from sentence_transformers import SentenceTransformer
from optimum.onnxruntime import ORTModelForFeatureExtraction
from transformers import AutoTokenizer


def export_model_to_onnx(
    model_name: str = "sentence-transformers/all-MiniLM-L6-v2",
    output_dir: str = "models/onnx"
):
    """
    Export a sentence-transformers model to ONNX format.
    
    Args:
        model_name: HuggingFace model identifier
        output_dir: Directory to save the ONNX model
    """
    print(f"Loading model: {model_name}")
    
    # Create output directory
    output_path = Path(output_dir)
    output_path.mkdir(parents=True, exist_ok=True)
    
    # Load the sentence-transformers model to verify it works
    st_model = SentenceTransformer(model_name)
    
    # Test the model
    test_sentence = "This is a test sentence."
    embedding = st_model.encode(test_sentence)
    print(f"Model loaded successfully. Embedding dimension: {len(embedding)}")
    
    # Load tokenizer and model for ONNX export
    print("Preparing model for ONNX export...")
    tokenizer = AutoTokenizer.from_pretrained(model_name)
    
    # Export to ONNX using Optimum
    print(f"Exporting model to ONNX format at: {output_path}")
    ort_model = ORTModelForFeatureExtraction.from_pretrained(
        model_name,
        export=True
    )
    
    # Save the ONNX model and tokenizer
    ort_model.save_pretrained(output_path)
    tokenizer.save_pretrained(output_path)
    
    print(f"✓ Model exported successfully to {output_path}")
    print(f"✓ Files created:")
    for file in output_path.iterdir():
        if file.is_file():
            print(f"  - {file.name}")
    
    # Verify the exported model
    print("\nVerifying exported model...")
    from optimum.onnxruntime import ORTModelForFeatureExtraction
    from transformers import AutoTokenizer
    
    loaded_tokenizer = AutoTokenizer.from_pretrained(output_path)
    loaded_model = ORTModelForFeatureExtraction.from_pretrained(output_path)
    
    # Test with the same sentence
    inputs = loaded_tokenizer(test_sentence, return_tensors="pt")
    outputs = loaded_model(**inputs)
    print(f"✓ Exported model verified successfully!")
    print(f"  Output shape: {outputs.last_hidden_state.shape}")
    
    return output_path


def main():
    """Main entry point."""
    print("=" * 60)
    print("Sentence Transformers to ONNX Exporter")
    print("=" * 60)
    
    export_model_to_onnx()
    
    print("\n" + "=" * 60)
    print("Export completed successfully!")
    print("=" * 60)
    print("\nTo use the model:")
    print("1. The ONNX model is saved in the 'models/onnx' directory")
    print("2. You can load it using ONNX Runtime in your Java/Kotlin app")
    print("3. The tokenizer configuration is also saved for preprocessing")


if __name__ == "__main__":
    main()
