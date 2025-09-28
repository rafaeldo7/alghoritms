import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
import os

SCRIPT_DIR = os.path.dirname(os.path.abspath(__file__))
CSV_FILE = os.path.join(SCRIPT_DIR, 'metrics.csv')
OUTPUT_DIR = os.path.join(SCRIPT_DIR, 'plots')

def create_plots():


    if not os.path.exists(OUTPUT_DIR):
        os.makedirs(OUTPUT_DIR)

    df = pd.read_csv(CSV_FILE)
    print(" Data successfully loaded. Generating plots...")

    sns.set_theme(style="whitegrid")


    plt.figure(figsize=(12, 7))
    sns.lineplot(data=df, x='Size', y='Time(ns)', hue='Algorithm', marker='o', lw=2)
    plt.xscale('log')
    plt.yscale('log')
    plt.title('Performance: Time vs. Input Size (Logarithmic Scale)', fontsize=16)
    plt.xlabel('Array Size (N)', fontsize=12)
    plt.ylabel('Time (nanoseconds)', fontsize=12)
    plt.legend(title='Algorithm')
    time_plot_path = os.path.join(OUTPUT_DIR, 'time_vs_n.png')
    plt.savefig(time_plot_path)
    plt.close()

    # "Recursion Depth vs. N" Plot
    plt.figure(figsize=(12, 7))
    recursive_df = df[df['MaxDepth'] > 0] # Exclude non-recursive algorithms
    sns.lineplot(data=recursive_df, x='Size', y='MaxDepth', hue='Algorithm', marker='o', lw=2)
    plt.xscale('log')
    plt.title('Efficiency: Recursion Depth vs. Input Size (Logarithmic Scale)', fontsize=16)
    plt.xlabel('Array Size (N)', fontsize=12)
    plt.ylabel('Maximum Recursion Depth', fontsize=12)
    plt.legend(title='Algorithm')
    depth_plot_path = os.path.join(OUTPUT_DIR, 'depth_vs_n.png')
    plt.savefig(depth_plot_path)
    plt.close()

    # "Number of Comparisons vs. N" Plot
    plt.figure(figsize=(12, 7))
    sns.lineplot(data=df, x='Size', y='Comparisons', hue='Algorithm', marker='o', lw=2)
    plt.xscale('log')
    plt.yscale('log')
    plt.title('Complexity: Comparisons vs. Input Size (Logarithmic Scale)', fontsize=16)
    plt.xlabel('Array Size (N)', fontsize=12)
    plt.ylabel('Number of Comparisons', fontsize=12)
    plt.legend(title='Algorithm')
    comparisons_plot_path = os.path.join(OUTPUT_DIR, 'comparisons_vs_n.png')
    plt.savefig(comparisons_plot_path)
    plt.close()

if __name__ == "__main__":
    create_plots()